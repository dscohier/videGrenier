package be.icc.controller;

import be.icc.dto.BidderDto;
import be.icc.dto.OrdersDto;
import be.icc.dto.ProductDto;
import be.icc.dto.UserDto;
import be.icc.entity.Bidder;
import be.icc.entity.Panier;
import be.icc.entity.Product;
import be.icc.entity.User;
import be.icc.enumClass.CategoryEnum;
import be.icc.enumClass.SellOrNotEnum;
import be.icc.enumClass.TypeOfSaleEnum;
import be.icc.form.*;
import be.icc.model.FileModel;
import be.icc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import java.util.*;

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
    CartService cartService;
    @Autowired
    OrderService orderService;
    private int SIZE_PAGE = 2;

    @RequestMapping("/products")
    public String products(HttpServletRequest request, Model model, @RequestParam(required = false) String category, String title, @RequestParam(required = false) Integer pageNumber) {
        Page<Product> productsPage = null;
        Pageable page;
        request.getSession().setAttribute("filterProductsForm", null);
        FilterProductsForm filterProductsForm = new FilterProductsForm();
        if (pageNumber != null) {
            page = new PageRequest(pageNumber, SIZE_PAGE);
        } else {
            page = new PageRequest(0, SIZE_PAGE);
        }
        if (isNotBlank(category)) {
            model.addAttribute("category", category);
            CategoryEnum categoryEnum;
            try {
                categoryEnum = CategoryEnum.valueOf(category);
            } catch (Exception e) {
                return "redirect:";
            }
            productsPage = productService.findByCategoryAndSalable(categoryEnum, page);
            String categories[] = {category};
            filterProductsForm.setCategories(categories);
        } else {
            if (isNotBlank(title)) {
                model.addAttribute("title", title);
                filterProductsForm.setTitle(title);
                productsPage = productService.findProductsByCriteria(filterProductsForm, page);
            } else {
                productsPage = productService.findAllSalableProduct(page);
            }
        }
        if (productsPage == null || productsPage.getContent().isEmpty()) {
            model.addAttribute("error", "error.products.noProducts");
        } else {
            model.addAttribute("productsPage", productsPage);
            initialisePaging(model, productsPage, pageNumber);
        }
        initFilterProducts(model, filterProductsForm);
        return "products";
    }

    @RequestMapping("/title")
    public String productsTitle(HttpServletRequest request, Model model,  @RequestParam String title) {
        return products(null, model, null, title, 0);
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

    private void initFilterPurchases(Model model, FilterPurchasesForm filterPurchasesForm) {
        model.addAttribute("filterPurchasesForm", filterPurchasesForm);
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
    public String filterProducts(HttpServletRequest request, @ModelAttribute("filterProductsForm") @Valid FilterProductsForm filterProductsForm, Model model, @RequestParam(required = false) Integer pageNumber) {
        Pageable page;
        if (pageNumber != null) {
            filterProductsForm = (FilterProductsForm) request.getSession().getAttribute("filterProductsForm");
            page = new PageRequest(pageNumber, SIZE_PAGE);
        } else {
            request.getSession().setAttribute("filterProductsForm", filterProductsForm);
            page = new PageRequest(0, SIZE_PAGE);
        }
        Page<Product> productsPage  = productService.findProductsByCriteria(filterProductsForm, page);
        initFilterProducts(model, filterProductsForm);
        if (productsPage == null || productsPage.getContent().isEmpty()) {
            model.addAttribute("error", "error.products.noProducts");
        } else {
            model.addAttribute("productsPage", productsPage);
            model.addAttribute("filter", true);
            initialisePaging(model, productsPage, pageNumber);
        }
        return "products";
    }

    @RequestMapping("/filterSales")
    public String filterSales(HttpServletRequest request, @ModelAttribute("filterSalesForm") @Valid FilterSalesForm filterSalesForm, Model model, @RequestParam(required = false) Integer pageNumber) {
        Pageable page;
        if (pageNumber != null) {
            filterSalesForm = (FilterSalesForm) request.getSession().getAttribute("filterSalesForm");
            page = new PageRequest(pageNumber, SIZE_PAGE);
        } else {
            request.getSession().setAttribute("filterSalesForm", filterSalesForm);
            page = new PageRequest(0, SIZE_PAGE);
        }
        if ("anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
            return "redirect:/connect";
        }
        Page<Product> productsPage = productService.findSalesByCriteria(filterSalesForm, ((UserDto)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername(), page);
        initFilterSales(model, filterSalesForm);
        if (productsPage == null || productsPage.getContent().isEmpty()) {
            model.addAttribute("error", "error.products.noProductFilter");
        } else {
            model.addAttribute("productsPage", productsPage);
            model.addAttribute("filter", true);
            initialisePaging(model, productsPage, pageNumber);
        }
        return "mySales";
    }

    @RequestMapping("/filterPurchases")
    public String filterPurchases(HttpServletRequest request, @ModelAttribute("filterPurchasesForm") @Valid FilterPurchasesForm filterPurchasesForm, Model model, @RequestParam(required = false) Integer pageNumber) {
        if ("anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
            return "redirect:/connect";
        }
        User user = userService.findEntityById(((UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
        List<Bidder> bidders = bidderService.findByUser(user);
        List<ProductDto> products = new ArrayList<>();
        List<OrdersDto> orders = orderService.findByUser(user.getId());
        Pageable page;
        if (pageNumber != null) {
            filterPurchasesForm = (FilterPurchasesForm) request.getSession().getAttribute("filterPurchasesForm");
            page = new PageRequest(pageNumber, SIZE_PAGE);
        } else {
            request.getSession().setAttribute("filterPurchasesForm", filterPurchasesForm);
            page = new PageRequest(0, SIZE_PAGE);
        }
        for (OrdersDto ordersDto : orders) {
            products.addAll(ordersDto.getProducts());
        }
        if (filterPurchasesForm.isCurrentAuctions()) {
            products.addAll(productService.findDistinctProductByBiddersInAndEndDateAfter(bidders, new Date()));
        }
        if (products.isEmpty()) {
            model.addAttribute("error", "error.products.noPurchases");
        } else {
            Page<ProductDto> productsPage = new PageImpl<>(products, page, products.size());
            model.addAttribute("productsPage", productsPage);
            initialisePagingDto(model, productsPage, pageNumber);
        }
        return "myPurchases";
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
                      RedirectAttributes attr, @RequestParam MultipartFile file) {
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
            if (!product.getSeller().getUsername().equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
                product.setView(product.getView()+1);
                productService.update(product.toEntity());
            }
            model.addAttribute("user", userDto);
            boolean isInCart = false;
            for (ProductDto productFromPanier : userDto.getPanier().getProducts()) {
                if (productFromPanier.getId() == product.getId()) {
                    isInCart = true;
                }
            }
            model.addAttribute("isInCart", isInCart);
        }
         return "details";
    }


    @RequestMapping(value = "/bid", method = RequestMethod.POST)
    public String bid(@ModelAttribute("bidForm")  BidForm bidForm) {
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

    @RequestMapping(value = "/addToCart", method = RequestMethod.POST)
    public String addToCart(@RequestParam("idProduct") Long idProduct) {
        Product product = productService.findEntityById(idProduct);
        if ("anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
            return "redirect:/connect";
        }

        if (product.isSell()) {
            // TODO return home with error le produit est déja vendu
            return "redirect:/";
        }

        UserDto user = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Panier panier = cartService.findEntityById(user.getPanier().getId());
        panier.getProducts().add(product);
        user.setPanier(cartService.update(panier).toDto());
        return "redirect:/product/details?id=" + product.getId();
    }

    @RequestMapping(value = "/deleteFromCart", method = RequestMethod.POST)
    public String deleteFromCart(@RequestParam("idProduct") Long idProduct) {
        if ("anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
            return "redirect:/connect";
        }

        UserDto user = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Panier panier = cartService.findEntityById(user.getPanier().getId());
        for (Product product : panier.getProducts()) {
            if (product.getId() == idProduct) {
                panier.getProducts().remove(product);
                break;
            }
        }
        user.setPanier(cartService.update(panier).toDto());
        return "redirect:/product/cart";
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String order() {
        if ("anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
            return "redirect:/connect";
        }

        UserDto user = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Panier panier = cartService.findEntityById(user.getPanier().getId());
        OrdersDto order = new OrdersDto();
        order.setDate(new Date());
        order.setUser(user);
        order.setProducts(user.getPanier().getProducts());
        orderService.add(order);
        for (Product product : panier.getProducts()) {
            product.setSell(true);
            productService.update(product);
        }
        panier.setProducts(new HashSet<>());
        user.setPanier(cartService.update(panier).toDto());
        return "redirect:/product/myPurchases";
    }

    @RequestMapping("/mySales")
    public String mySales(HttpServletRequest request, Model model, @RequestParam(required = false) Integer pageNumber) {
        if ("anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
            return "redirect:/connect";
        }
        request.getSession().setAttribute("filterSalesForm", null);
        Pageable page;
        if (pageNumber != null) {
            page = new PageRequest(pageNumber, SIZE_PAGE);
        } else {
            page = new PageRequest(0, SIZE_PAGE);
        }
        Page<Product> productsPage = productService.findBySeller(userService.findByUsername(((UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()), page);
        if (productsPage == null || productsPage.getContent().isEmpty()) {
            model.addAttribute("error", "error.products.noSales");
        } else {
            model.addAttribute("productsPage", productsPage);
            initialisePaging(model, productsPage, pageNumber);
        }
        initFilterSales(model, new FilterSalesForm());
        return "mySales";
    }

    @RequestMapping("/myPurchases")
    public String myPurchases(HttpServletRequest request, Model model, @RequestParam(required = false) Integer pageNumber) {
        if ("anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
            return "redirect:/connect";
        }
        request.getSession().setAttribute("filterPurchasesForm", null);
        Pageable page;
        if (pageNumber != null) {
            page = new PageRequest(pageNumber, SIZE_PAGE);
        } else {
            page = new PageRequest(0, SIZE_PAGE);
        }
        User user = userService.findEntityById(((UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
        List<Bidder> bidders = bidderService.findByUser(user);
        List<ProductDto> products = new ArrayList<>();
        List<OrdersDto> orders = orderService.findByUser(user.getId());
        for (OrdersDto ordersDto : orders) {
            products.addAll(ordersDto.getProducts());
        }
        products.addAll(productService.findDistinctProductByBiddersInAndEndDateAfter(bidders, new Date()));
        if (products.isEmpty()) {
            model.addAttribute("error", "error.products.noPurchases");
        } else {
            Page<ProductDto> productsPage = new PageImpl<>(products, page, products.size());
            model.addAttribute("productsPage", productsPage);
            initialisePagingDto(model, productsPage, pageNumber);
        }
        initFilterPurchases(model, new FilterPurchasesForm());
        return "myPurchases";
    }
// TODO REPLACE
    private void initialisePaging(Model model, List<ProductDto> products) {
        model.addAttribute("size", (int) Math.ceil(products.size() / 9.0));
        model.addAttribute("currentPage", 1);
        if (products.size() > 9) {
            model.addAttribute("products", products.subList(0, 9));
        } else {
            model.addAttribute("products", products);
        }
    }

    private void initialisePaging(Model model, Page<Product> page, Integer pageNumber) {
        List<ProductDto> products = new ArrayList<>();
        if (pageNumber == null) {
            pageNumber = 1;
        } else {
            pageNumber++;
        }
       for (Product product : page.getContent()) {
           products.add(product.toDto());
       }
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("products", products);
    }

    // TODO refactor difficult logic
    private void initialisePagingDto(Model model, Page<ProductDto> page, Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = 1;
        } else {
            pageNumber++;
        }
        List<ProductDto> products;
        if (page.getContent().size()/SIZE_PAGE  < pageNumber) {
            products = page.getContent().subList((pageNumber-1) * SIZE_PAGE, page.getNumberOfElements());
        } else {
            products =  page.getContent().subList((pageNumber-1) * SIZE_PAGE, (pageNumber-1) * SIZE_PAGE + SIZE_PAGE);
        }
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("products", products);
    }

    @ExceptionHandler(Exception.class)
    public String ErreurExample(HttpServletRequest request, Model model, Exception exception) {
        model.addAttribute("exception",exception);
        model.addAttribute("url",request.getRequestURL());

        return "error";
    }

    @RequestMapping("/cart")
    public String cart(Model model) {
        if ("anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
            return "redirect:/connect";
        }
        Set<ProductDto> products = ((UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPanier().getProducts();
        int total = 0;
        if (products.isEmpty()) {
            model.addAttribute("error", "error.cart.empty");
        } else {
            model.addAttribute("products", products);
            for (ProductDto product : products) {
                total += product.getPrice();
            }
        }
        model.addAttribute("total", total);
        return "cart";
    }
}
