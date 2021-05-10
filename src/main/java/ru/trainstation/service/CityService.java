package ru.trainstation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.trainstation.data.entity.City;
import ru.trainstation.data.repository.CityRepository;
import ru.trainstation.exception.NotFountException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    /**
     * Метод для получения всех записей
     *
     * @return список всех существующих записей
     */
    public List<City> getAllCities() {
        var result = new ArrayList<City>();
        cityRepository.findAll().forEach(result::add);
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
    public City findById(Long id) throws NotFountException {
        return cityRepository.findById(id).orElseThrow(NotFountException::new);
    }

    /**
     * Добавление новой записи
     *
     * @param city добавляемая запись
     *
     * @return добавленная запись
     */
    public City save(City city) {
        return cityRepository.save(city);
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
        return cityRepository.existsById(id);
    }

    /**
     * Редактирование записи
     *
     * @param city редактируемая запись
     *
     * @return отредактированная запись
     *
     * @throws NotFountException если записи в базе
     * не существует, то выбрасывается исключение
     */
    public City update(City city) throws NotFountException {
        if (city.getCityId() == null || !existsById(city.getCityId())) {
            throw new NotFountException();
        }
        return cityRepository.save(city);
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
        cityRepository.deleteById(id);
    }
}
