package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.CalcReport;
import com.example.demo.dto.Product;
import com.example.demo.service.HibernateSearchService;
import com.example.demo.service.ProductService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ProductController {

    @Autowired
    private HibernateSearchService searchservice;

    @Autowired
    private ProductService productservice;

    @RequestMapping(value = "/", method = RequestMethod.GET,produces={"application/json", "application/csv"})
    public String search(@RequestParam(value = "search", required = false) String q, Model model) {
        List<Product> searchResults = null;
        try {
            productservice.addProducts();
            searchResults = searchservice.fuzzySearch(q);

        } catch (Exception ex) {
            
        }
        System.out.println("product"+searchResults);
        model.addAttribute("search", searchResults);
        return "index";

    }
    
    @RequestMapping(value="/myexcel", method=RequestMethod.GET,produces={MediaType.APPLICATION_JSON_VALUE,"application/ms-excel"})
    public ResponseEntity<?> getMyData(HttpServletRequest request, HttpServletResponse response) throws SQLException{
        Map<String, Object> model = new HashMap<String, Object>();
        //Sheet Name
        model.put("sheetname", "TestSheetName");
        //Headers List
        List<String> headers = new ArrayList<String>();
        headers.add("siteId");
        headers.add("expression");
        headers.add("TagName");
        model.put("headers", headers);
        //Results Table (List<Object[]>)
        List<List<CalcReport>> results = new ArrayList<List<CalcReport>>();
        List<String> l1 = new ArrayList<String>();
        List<CalcReport> d1 = new ArrayList<>();
        l1.add("A1");
        l1.add("B1");
        l1.add("C1");
        CalcReport c1 = new CalcReport();
        c1.setSiteId("s1");
        c1.setExpression("s1+s2");
        c1.setTagname("tagA");
        c1.setTagVal(l1);
        d1.add(c1);
        results.add(d1);
        
        List<String> l2 = new ArrayList<String>();
        List<CalcReport> d2 = new ArrayList<>();
        l2.add("A2");
        l2.add("B2");
        l2.add("C2");
        CalcReport c2 = new CalcReport();
        c2.setSiteId("s3");
        c2.setExpression("s3+s4");
        c2.setTagname("tagB");
        c2.setTagVal(l1);
        d2.add(c2);
        
        results.add(d2);
        model.put("results",results);
        if("application/ms-excel".equals(request.getContentType())) {
        response.setContentType( "application/ms-excel" );
        response.setHeader( "Content-disposition", "attachment; filename=myfile.xls" );         
        return ResponseEntity.ok(new ModelAndView(new MyExcelView(), model));
        }
        else {
        	return ResponseEntity.ok(results);
        }
        	
    }

}
