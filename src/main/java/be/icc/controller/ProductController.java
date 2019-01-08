package be.icc.controller;

import be.icc.dto.ProductDto;
import be.icc.dto.UserDto;
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

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
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
        } else {
            if (!model.containsAttribute("addProductForm")) {
                model.addAttribute("addProductForm", new AddProductForm());
            }
            if (!model.containsAttribute("fileModel")) {
                FileModel fileModel = new FileModel();
                model.addAttribute("fileModel", fileModel);
            }
            if (!model.containsAttribute("categoryList")) {
                model.addAttribute("categoryList", CategoryEnum.values());
            }
            if (isNotBlank(error)) {
                switch (error) {
                    case "NoPicture" : model.addAttribute("error", "error.add.noPicture");
                                       break;
                    case "PictureFormat" : model.addAttribute("error", "error.add.pictureFormat");
                                           break;
                    case "PictureError" : model.addAttribute("error", "error.add.pictureError");
                                          break;
                }
            }
            return "addProduct";
        }
    }

    @RequestMapping("/add")
    public String add(@ModelAttribute("addProductForm") @Valid AddProductForm addProductForm, BindingResult result,
                      RedirectAttributes attr) {
        if (result.hasErrors() || addProductForm.getFile().isEmpty()) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.addProductForm", result);
            attr.addFlashAttribute("addProductForm", addProductForm);
            if (addProductForm.getFile().isEmpty()) {
                return "redirect:/product/newProduct?error=NoPicture";
            } else {
                return "redirect:/product/newProduct";
            }
        }
        MultipartFile multipartFile = addProductForm.getFile();
        String[] location = addProductForm.getFile().getOriginalFilename().split("\\\\");
        String fileName = location[location.length-1];
        if(!contentTypes.contains(fileName.split("\\.")[1])) {
            attr.addFlashAttribute("addProductForm", addProductForm);
            return "redirect:/product/newProduct?error=PictureFormat";
        }
        String userName = ((UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        String directoryName = "D:/tmp/" + userName + "/";

        File directory = new File(String.valueOf(directoryName + ""));

        if(!directory.exists()) {
            directory.mkdirs();
        }

        try {
            InputStream inputStream = multipartFile.getInputStream();
            File file = new File(directoryName + fileName);
            Files.copy(inputStream, file.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            attr.addFlashAttribute("addProductForm", addProductForm);
            return "redirect:/product/newProduct?error=PictureError";
        }
        ProductDto productDto = new ProductDto();
        productDto.setDescription(addProductForm.getDescription());
        productDto.setName(addProductForm.getName());
        productDto.setAuction(addProductForm.isAuction());
        productDto.setPicture(directoryName + fileName);
        productDto.setPrice(addProductForm.getPrice());
        productDto.setCategory(categoryService.createOrGetIfExists(addProductForm.getCategory()));
        productDto.setSeller(userService.findByUsername(((UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()));
        productService.add(productDto);
        // FIXME return de detail of product
        return "redirect:/";
        }
    }
