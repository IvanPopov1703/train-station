package ru.trainstation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.trainstation.data.entity.Discount;
import ru.trainstation.data.repository.DiscountRepository;
import ru.trainstation.data.repository.DiscountRepository;
import ru.trainstation.exception.NotFountException;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiscountService {

    private final DiscountRepository discountRepository;

    @Autowired
    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    /**
     * Метод для получения всех записей
     *
     * @return список всех существующих записей
     */
    public List<Discount> getAllDiscounts() {
        var result = new ArrayList<Discount>();
        discountRepository.findAll().forEach(result::add);
        return result;
    }

    /**
     * Метод для получения запись по id
     *
     * @param id номер запими
     *
     * @return найденная запись
     *
     * @throws NotFountException если запись с заданным
     * id не существует, то выбрасывается исключение
     */
    public Discount findById(Long id) throws NotFountException {
        return discountRepository.findById(id).orElseThrow(NotFountException::new);
    }

    /**
     * Добавление новой записи
     *
     * @param discount добавляемая запись
     *
     * @return добавленная запись
     */
    public Discount save(Discount discount) {
        return discountRepository.save(discount);
    }

    /**
     * Проверка существования записи в базе
     *
     * @param id проверямой записи
     *
     * @return true - запись существует,
     *         false - запись не найдена
     */
    public boolean existsById(Long id) {
        return discountRepository.existsById(id);
    }

    /**
     * Редактирование записи
     *
     * @param discount редактируемая запись
     *
     * @return отредактированная запись
     *
     * @throws NotFountException если записи в базе
     * не существует, то выбрасывается исключение
     */
    public Discount update(Discount discount) throws NotFountException {
        if (discount.getDiscountId() == null || !existsById(discount.getDiscountId())) {
            throw new NotFountException();
        }
        return discountRepository.save(discount);
    }

    /**
     * Удаление записи по id
     *
     * @param id удаляемой записи
     *
     * @throws NotFountException если записи
     * с заданным id не существует, то выбрасывается
     * исключение
     */
    public void deleteById(Long id) throws NotFountException {
        if (!existsById(id)) {
            throw new NotFountException();
        }
        discountRepository.deleteById(id);
    }
}
