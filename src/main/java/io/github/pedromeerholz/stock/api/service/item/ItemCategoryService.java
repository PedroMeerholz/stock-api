package io.github.pedromeerholz.stock.api.service.item;

import io.github.pedromeerholz.stock.api.model.item.itemCategory.ItemCategory;
import io.github.pedromeerholz.stock.api.model.item.itemCategory.dto.ItemCategoryDto;
import io.github.pedromeerholz.stock.api.repository.UserRepository;
import io.github.pedromeerholz.stock.api.repository.item.ItemCategoryRepository;
import io.github.pedromeerholz.stock.validations.AuthorizationTokenValidator;
import io.github.pedromeerholz.stock.validations.itemValidations.CategoryValidation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemCategoryService {
    private final ItemCategoryRepository itemCategoryRepository;
    private final UserRepository userRepository;
    private final CategoryValidation categoryValidation;
    private final AuthorizationTokenValidator authorizationTokenValidator;

    public ItemCategoryService(ItemCategoryRepository itemCategoryRepository, UserRepository userRepository) {
        this.itemCategoryRepository = itemCategoryRepository;
        this.userRepository = userRepository;
        this.authorizationTokenValidator = new AuthorizationTokenValidator();
        this.categoryValidation = new CategoryValidation();
    }

    public HttpStatus createItemCategory(ItemCategoryDto itemCategoryDto, String email, String authorizationToken) {
        try {
            boolean isUserAuthorized = this.authorizationTokenValidator.validateAuthorizationToken(this.userRepository, email, authorizationToken);
            if (isUserAuthorized == false) {
                return HttpStatus.UNAUTHORIZED;
            }
            boolean isValidCategory = this.categoryValidation.validateCategory(this.itemCategoryRepository, itemCategoryDto.getCategory());
            if (isValidCategory) {
                ItemCategory itemCategory = new ItemCategory();
                itemCategory.setCategory(itemCategoryDto.getCategory());
                itemCategory.setEnabled(itemCategoryDto.isEnabled());
                this.itemCategoryRepository.save(itemCategory);
                return HttpStatus.OK;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.NOT_ACCEPTABLE;
    }

    public List<ItemCategory> listAll() {
        return this.itemCategoryRepository.findAll();
    }

    public HttpStatus updateItemCategory(ItemCategoryDto itemCategoryDto, String categoryToUpdate, String email, String authorizationToken) {
        try {
            boolean isUserAuthorized = this.authorizationTokenValidator.validateAuthorizationToken(this.userRepository, email, authorizationToken);
            if (isUserAuthorized == false) {
                return HttpStatus.UNAUTHORIZED;
            }
            Optional<ItemCategory> optionalCurrentItemCategory = this.itemCategoryRepository.findByCategory(categoryToUpdate);
            if (optionalCurrentItemCategory.isPresent()) {
                ItemCategory currentItemCategory = optionalCurrentItemCategory.get();
                ItemCategory updatedItemCategory = new ItemCategory();
                updatedItemCategory.setId(currentItemCategory.getId());
                updatedItemCategory.setCategory(itemCategoryDto.getCategory());
                updatedItemCategory.setEnabled(itemCategoryDto.isEnabled());
                this.itemCategoryRepository.save(updatedItemCategory);
                return HttpStatus.ACCEPTED;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.NOT_MODIFIED;
    }
}