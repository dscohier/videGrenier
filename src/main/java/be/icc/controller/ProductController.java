package be.icc.controller;

import be.icc.dto.ProductDto;
import be.icc.dto.UserDto;
import be.icc.entity.Product;
import be.icc.form.AddProductForm;
import be.icc.model.FileModel;
import be.icc.service.CategoryService;
import be.icc.service.ProductService;
import be.icc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.apache.commons.lang.StringUtils.isNotBlank;


/**
 * Created by Scohier Dorian on 18-12-18.
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    UserService userService;

    private static final List<String> contentTypes = Arrays.asList("png", "jpeg", "jpg");

    @RequestMapping("/newProduct")
    public String newProduct(Model model, @RequestParam(required = false) String error) {
        if ("anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
            return "redirect:/connect";
        }
        if (!model.containsAttribute("addProductForm")) {
            model.addAttribute("addProductForm", new AddProductForm());
        }
        initialiseModelForAddAndUpdate(model, error);
        return "addProduct";
    }

    @RequestMapping("/updateProduct")
    public String updateProduct(@ModelAttribute("addProductForm")  AddProductForm updateProductForm,Model model, @RequestParam(required = false) String error) {
        ProductDto productDto = productService.findById(updateProductForm.getId());
        if (!productDto.getSeller().getUsername().equals(((UserDto)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername())) {
            return "redirect:/product/details?id=" + updateProductForm.getId();
        }
        if (!model.containsAttribute("addProductForm") || updateProductForm != null) {
            AddProductForm addProductForm = new AddProductForm();
            addProductForm.setId(productDto.getId());
            addProductForm.setCategory(productDto.getCategory().getCategory());
            addProductForm.setDescription(productDto.getDescription());
            addProductForm.setName(productDto.getName());
            addProductForm.setPrice(productDto.getPrice());
            model.addAttribute("addProductForm", addProductForm);
        }
        initialiseModelForAddAndUpdate(model, error);
        return "addProduct";
    }

    private void initialiseModelForAddAndUpdate(Model model, String error) {
        if (!model.containsAttribute("fileModel")) {
            FileModel fileModel = new FileModel();
            model.addAttribute("fileModel", fileModel);
        }
        if (!model.containsAttribute("categoryList")) {
            model.addAttribute("categoryList", CategoryEnum.values());
        }
        if (isNotBlank(error)) {
            switch (error) {
                case "NoPicture":
                    model.addAttribute("error", "error.add.noPicture");
                    break;
                case "PictureFormat":
                    model.addAttribute("error", "error.add.pictureFormat");
                    break;
                case "PictureError":
                    model.addAttribute("error", "error.add.pictureError");
                    break;
            }
        }
    }

    @RequestMapping("/add")
    public String add(@ModelAttribute("addProductForm") @Valid AddProductForm addProductForm, BindingResult result,
                      RedirectAttributes attr, HttpServletRequest request, @RequestParam MultipartFile file) {
        String redirect = checkError(result, attr, addProductForm);
        if (redirect != null) {
            return redirect;
        }

        String filePath = uploadFile(addProductForm, attr, file);

        ProductDto productDto = new ProductDto();
        productDto.setDescription(addProductForm.getDescription());
        productDto.setName(addProductForm.getName());
        productDto.setAuction(addProductForm.isAuction());
        productDto.setPicture(filePath);
        productDto.setPrice(addProductForm.getPrice());
        productDto.setCategory(categoryService.createOrGetIfExists(addProductForm.getCategory()));
        productDto.setSeller(userService.findByUsername(((UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()));
        productDto.setCreationDate(new Date());
        productDto = productService.add(productDto);
        return "redirect:/product/details?id=" + productDto.getId();
    }

    @RequestMapping("/update")
    public String update(@ModelAttribute("addProductForm") @Valid AddProductForm addProductForm, BindingResult result,
                      RedirectAttributes attr, HttpServletRequest request, @RequestParam MultipartFile file) {
        String redirect = checkError(result, attr, addProductForm);
        if (redirect != null) {
            return redirect;
        }
        String filePath = null;
        if (isNotBlank(file.getOriginalFilename())) {
            filePath = uploadFile(addProductForm, attr, file);
        }
        Product product = productService.findEntityById(addProductForm.getId());
        product.setDescription(addProductForm.getDescription());
        product.setName(addProductForm.getName());
        product.setAuction(addProductForm.isAuction());
        if(isNotBlank(filePath)) {
            product.setPicture(filePath);
        }
        product.setPrice(addProductForm.getPrice());
        product.setCategory(categoryService.createOrGetIfExists(addProductForm.getCategory()).toEntity());
        ProductDto productDto = productService.update(product);
        return "redirect:/product/details?id=" + productDto.getId();
    }

    private String uploadFile(AddProductForm addProductForm, RedirectAttributes attr, MultipartFile file) {
        String[] location = addProductForm.getFile().getOriginalFilename().split("\\\\");
        String fileName = location[location.length - 1];
        if (!contentTypes.contains(fileName.split("\\.")[1])) {
            attr.addFlashAttribute("addProductForm", addProductForm);
            return "redirect:/product/newProduct?error=PictureFormat";
        }
        String userName = ((UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        String directoryName = "D:/tmp/img/" + userName + "/";
        File dir = new File(directoryName);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);

        //write uploaded image to disk
        try {
            try (InputStream is = file.getInputStream(); BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
                int i;
                while ((i = is.read()) != -1) {
                    stream.write(i);
                }
                stream.flush();
            }
        } catch (IOException e) {
            System.out.println("error : " + e.getMessage());
        }
        return dir.getAbsolutePath() + "\\" + fileName;
    }

    private String checkError(BindingResult result, RedirectAttributes attr, AddProductForm addProductForm) {
        if (result.hasErrors() || (addProductForm.getFile().isEmpty() && addProductForm.getId() == null)) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.addProductForm", result);
            attr.addFlashAttribute("addProductForm", addProductForm);
            if (addProductForm.getFile().isEmpty()) {
                return "redirect:/product/newProduct?error=NoPicture";
            } else {
                return "redirect:/product/newProduct";
            }
        }
        return null;
    }

    @RequestMapping("/details")
    public String details(Model model, @RequestParam(required = true) Long id) {
        ProductDto product = productService.findById(id);
        model.addAttribute("product", product);
        String picture = "";
        try {
            String imgName = product.getPicture();
            BufferedImage bImage = ImageIO.read(new File(imgName));//give the path of an image
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", baos);
            baos.flush();
            byte[] imageInByteArray = baos.toByteArray();
            baos.close();
            picture = DatatypeConverter.printBase64Binary(imageInByteArray);
        }catch(IOException e){
            System.out.println("Error: "+e);
            model.addAttribute("error", "error.details.pictureError");
        }
        AddProductForm productForm = new AddProductForm();
        productForm.setId(id);
        model.addAttribute("picture", picture);
        model.addAttribute("updateProductForm", productForm);
        return "details";
    }
}
