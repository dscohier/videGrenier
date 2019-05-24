package be.icc.controller;

import be.icc.dto.BidderDto;
import be.icc.dto.ProductDto;
import be.icc.dto.UserDto;
import be.icc.entity.Panier;
import be.icc.entity.Product;
import be.icc.enumClass.CategoryEnum;
import be.icc.enumClass.SellOrNotEnum;
import be.icc.enumClass.TypeOfSaleEnum;
import be.icc.form.AddProductForm;
import be.icc.form.BidForm;
import be.icc.form.FilterProductsForm;
import be.icc.form.FilterSalesForm;
import be.icc.model.FileModel;
import be.icc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.apache.commons.lang.StringUtils.*;


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
    @Autowired
    BidderService bidderService;
    @Autowired
    FileService fileService;
    @Autowired
    BasketService basketService;

    @RequestMapping("/products")
    public String products(Model model, @RequestParam(required = false) String category) {
        List<ProductDto> products;
        if (isNotBlank(category)) {
            CategoryEnum categoryEnum;
            try {
                categoryEnum = CategoryEnum.valueOf(category);
            } catch (Exception e) {
                return "redirect:";
            }
            products = productService.findByCategoryAndSalable(categoryEnum);
        } else {
            products = productService.findAllSalableProduct();
        }
        if (products.isEmpty()) {
            model.addAttribute("error", "error.products.noProducts");
        } else {
            initialisePaging(model, products);
        }
        initFilterProducts(model, new FilterProductsForm());
        return "products";
    }

    private void initFilterProducts(Model model, FilterProductsForm filterProductsForm) {
        String[] categories = initCategories(model);
        if (filterProductsForm.getCategories() == null) {
            filterProductsForm.setCategories(categories);
        }

        String[] typeOfSales = initTypeOfSale(model);
        if (filterProductsForm.getTypeOfSale() == null) {
            filterProductsForm.setTypeOfSale(typeOfSales);
        }

        filterProductsForm.setCity("");
        model.addAttribute("filterProductsForm", filterProductsForm);
    }

    private void initFilterSales(Model model, FilterSalesForm filterSalesForm) {
        String[] categories = initCategories(model);
        if (filterSalesForm.getCategories() == null) {
            filterSalesForm.setCategories(categories);
        }

        String[] typeOfSales = initTypeOfSale(model);
        if (filterSalesForm.getTypeOfSale() == null) {
            filterSalesForm.setTypeOfSale(typeOfSales);
        }

        String[] sellOrNot = sellOrNot(model);
        if (filterSalesForm.getSellOrNot() == null) {
            filterSalesForm.setSellOrNot(sellOrNot);
        }

        model.addAttribute("filterSalesForm", filterSalesForm);
    }

    private String[] sellOrNot(Model model) {
        SellOrNotEnum[] sellOrNotEnums = SellOrNotEnum.values();
        String[] sellOrNot = new String[sellOrNotEnums.length];
        for (int i = 0; i < sellOrNotEnums.length; i++) {
            sellOrNot[i] = sellOrNotEnums[i].name();
        }
        model.addAttribute("sellOrNot", sellOrNot);
        return sellOrNot;
    }

    private String[] initTypeOfSale(Model model) {
        TypeOfSaleEnum[] typeOfSaleEnums = TypeOfSaleEnum.values();
        String[] typeOfSale = new String[typeOfSaleEnums.length];
        for (int i = 0; i < typeOfSaleEnums.length; i++) {
            typeOfSale[i] = typeOfSaleEnums[i].name();
        }
        model.addAttribute("typeOfSale", typeOfSale);
        return typeOfSale;
    }

    private String[] initCategories(Model model) {
        CategoryEnum[] categoryEnums = CategoryEnum.values();
        String[] categories = new String[categoryEnums.length];
        for (int i = 0; i < categoryEnums.length; i++) {
            categories[i] = categoryEnums[i].name();
        }
        model.addAttribute("categories", categories);
        return categories;
    }

    @RequestMapping("/filterProducts")
    public String filterProducts(@ModelAttribute("filterProductsForm") @Valid FilterProductsForm filterProductsForm, Model model) {
        List<ProductDto> products = productService.findProductsByCriteria(filterProductsForm);
        initFilterProducts(model, filterProductsForm);
        initialisePaging(model, products);
        model.addAttribute("error", "error.products.noProductFilter");
        return "products";
    }

    @RequestMapping("/filterSales")
    public String filterSales(@ModelAttribute("filterSalesForm") @Valid FilterSalesForm filterSalesForm, Model model) {
        if ("anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
            return "redirect:/connect";
        }
        List<ProductDto> products = productService.findSalesByCriteria(filterSalesForm, ((UserDto)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        initFilterSales(model, filterSalesForm);
        initialisePaging(model, products);
        model.addAttribute("error", "error.products.noProductFilter");
        return "mySales";
    }

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
            addProductForm.setDescription(productDto.getDescription().replace("<br>", ""));
            addProductForm.setName(productDto.getName());
            addProductForm.setPrice(productDto.getPrice());
            if (productDto.isAuction()) {
                addProductForm.setAuctionOrFixPrice("auction");
                addProductForm.setPriceAuction(productDto.getPrice());
                String dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm").format(productDto.getEndDate());
                addProductForm.setEndDateString(left(dateFormat, indexOf(dateFormat, " ")));
                addProductForm.setEndTimeString(dateFormat.substring(indexOf(dateFormat, " ") + 1));
            }
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
                      RedirectAttributes attr, @RequestParam MultipartFile file) {
        String redirect = checkError(result, attr, addProductForm);
        if (redirect != null) {
            return redirect;
        }

        String filePath = fileService.uploadFile(file, ((UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername(), addProductForm.getFile().getOriginalFilename());
        if (filePath.contains("error")) {
            attr.addFlashAttribute("addProductForm", addProductForm);
            return "redirect:/product/newProduct?error=PictureFormat";
        }
        ProductDto productDto = new ProductDto();
        productDto.setDescription(addProductForm.getDescription().replace("\n", "<br>"));
        productDto.setName(addProductForm.getName());
        if("auction".equals(addProductForm.getAuctionOrFixPrice())) {
            productDto.setAuction(true);
            productDto.setEndDate(addProductForm.getEndDate());
        }
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
        String filePath = "";
        if (isNotBlank(addProductForm.getFile().getOriginalFilename())) {
            filePath = fileService.uploadFile(file, ((UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername(), addProductForm.getFile().getOriginalFilename());
            if (filePath.contains("error")) {
                attr.addFlashAttribute("addProductForm", addProductForm);
                return "redirect:/product/newProduct?error=PictureFormat";
            }
        }
        Product product = productService.findEntityById(addProductForm.getId());
        product.setDescription(addProductForm.getDescription().replace("\n", "<br>"));
        product.setName(addProductForm.getName());
        if("auction".equals(addProductForm.getAuctionOrFixPrice())) {
            product.setAuction(true);
            product.setEndDate(addProductForm.getEndDate());
        }
        if(isNotBlank(filePath)) {
            product.setPicture(filePath);
        }
        product.setPrice(addProductForm.getPrice());
        product.setCategory(categoryService.createOrGetIfExists(addProductForm.getCategory()).toEntity());
        ProductDto productDto = productService.update(product);
        return "redirect:/product/details?id=" + productDto.getId();
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
    public String details(Model model, @RequestParam(required = true) Long id, @RequestParam(required = false) String error, @RequestParam(required = false) String success) {
        ProductDto product = productService.findById(id);
        model.addAttribute("product", product);
        AddProductForm productForm = new AddProductForm();
        productForm.setId(id);
        BidForm bidForm = new BidForm();
        bidForm.setIdProduct(id);
        bidForm.setNewPrice(product.getPrice()+1);
        model.addAttribute("updateProductForm", productForm);
        model.addAttribute("bidForm", bidForm);
        if (isNotBlank(error)) {
            switch (error) {
                case "InvalidBid":
                    model.addAttribute("error", "error.bid.invalidBid");
                    break;
                case "EndBid":
                    model.addAttribute("error", "error.bid.endBid");
                    break;
            }
        }
        if ((product.getBidders()).isEmpty()) {
            model.addAttribute("lastBidder", null);

        } else{
            model.addAttribute("lastBidder",product.getBidders().toArray()[product.getBidders().size()-1]);
        }

        if (!"anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
            UserDto userDto = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("user", userDto);
            boolean isInBasket = false;
            for (ProductDto productFromPanier : userDto.getPanier().getProducts()) {
                if (productFromPanier.getId() == product.getId()) {
                    isInBasket = true;
                }
            }
            model.addAttribute("isInBasket", isInBasket);
        }
         return "details";
    }


    @RequestMapping(value = "/bid", method = RequestMethod.POST)
    public String bid(@ModelAttribute("bidForm")  BidForm bidForm, Model model) {
        Product product = productService.findEntityById(bidForm.getIdProduct());
        if (product.getPrice() >= bidForm.getNewPrice()) {
            return "redirect:/product/details?id=" + product.getId() + "&error=InvalidBid";
        }
        if (product.getEndDate().before(new Date())) {
            return "redirect:/product/details?id=" + product.getId() + "&error=EndBid";
        }
        product.setPrice(bidForm.getNewPrice());
        BidderDto bidderDto = new BidderDto();
        bidderDto.setUser((UserDto)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        bidderDto.setPrice(bidForm.getNewPrice());
        bidderDto.setInsertionDate(new Date());
        bidderDto.setProductId(product.getId());
        product.getBidders().add(bidderService.save(bidderDto).toEntity());
        productService.update(product);
        return "redirect:/product/details?id=" + product.getId();
    }

    @RequestMapping(value = "/addToBasket", method = RequestMethod.POST)
    public String addToBasket(@RequestParam("idProduct") Long idProduct, Model model) {
        ProductDto product = productService.findById(idProduct);
        if ("anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
            return "redirect:/connect";
        }

        if (product.isSell()) {
            // TODO return home with error le produit est déja vendu
            return "redirect:/";
        }

        UserDto user = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Panier panier = basketService.findEntityById(user.getPanier().getId());
        panier.getProducts().add(productService.findEntityById(idProduct));
        user.setPanier(basketService.update(panier).toDto());
        // TODO return page panier
        return "redirect:/";
    }

    @RequestMapping("/mySales")
    public String mySales(Model model) {
        if ("anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
            return "redirect:/connect";
        }
        List<ProductDto> products = productService.findBySeller(userService.findByUsername(((UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()));
        if (products.isEmpty()) {
            model.addAttribute("error", "error.products.noSales");
        } else {
            initialisePaging(model, products);
        }
        initFilterSales(model, new FilterSalesForm());
        return "mySales";
    }

    private void initialisePaging(Model model, List<ProductDto> products) {
        model.addAttribute("size", (int) Math.ceil(products.size() / 9.0));
        model.addAttribute("currentPage", 1);
        if (products.size() > 9) {
            model.addAttribute("products", products.subList(0, 9));
        } else {
            model.addAttribute("products", products);
        }
    }

    @ExceptionHandler(Exception.class)
    public String ErreurExample(HttpServletRequest request, Model model, Exception exception) {
        model.addAttribute("exception",exception);
        model.addAttribute("url",request.getRequestURL());

        return "error";
    }
}
