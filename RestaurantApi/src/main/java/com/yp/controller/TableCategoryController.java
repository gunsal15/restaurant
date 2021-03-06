package com.yp.controller;

import com.yp.dto.TableCategoryDto;
import com.yp.service.TableCategoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@Api(tags = "TableCategory Controller")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/table_categories")
public class TableCategoryController {
    @Autowired
    private TableCategoryService tableCategoryService;
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public TableCategoryDto getTableCat(@PathVariable(value = "id") Long id, @RequestParam(value = "lang", defaultValue = "en") String lang){
        return tableCategoryService.getTableCategory(id, lang);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public List<TableCategoryDto> getCats(){
        return tableCategoryService.getAllTableCategories();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void addTableCat(@Valid  @RequestBody TableCategoryDto table, @RequestParam(value = "lang", defaultValue = "en") String lang){
        tableCategoryService.addTableCategory(table, lang);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void editTableCat(@PathVariable(value = "id") Long id,@Valid @RequestBody TableCategoryDto tableCat, @RequestParam(value = "lang", defaultValue = "en") String lang){
        tableCategoryService.editTableCategory(id, tableCat, lang);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteTableCat(@PathVariable(value = "id") Long id, @RequestParam(value = "lang", defaultValue = "en") String lang){
        tableCategoryService.deleteTableCategory(id, lang);
    }

}
