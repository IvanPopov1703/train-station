package ru.trainstation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.trainstation.data.entity.TypeRailwayCarriage;
import ru.trainstation.data.repository.TypeRailwayCarriageRepository;
import ru.trainstation.exception.NotFountException;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeRailwayCarriageService {

    private final TypeRailwayCarriageRepository typeRailwayCarriageRepository;

    @Autowired
    public TypeRailwayCarriageService(TypeRailwayCarriageRepository typeRailwayCarriageRepository) {
        this.typeRailwayCarriageRepository = typeRailwayCarriageRepository;
    }

    /**
     * Метод для получения всех записей
     *
     * @return список всех существующих записей
     */
    public List<TypeRailwayCarriage> getAllTypeRailwayCarriages() {
        var result = new ArrayList<TypeRailwayCarriage>();
        typeRailwayCarriageRepository.findAll().forEach(result::add);
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
    public TypeRailwayCarriage findById(Long id) throws NotFountException {
        return typeRailwayCarriageRepository.findById(id).orElseThrow(NotFountException::new);
    }

    /**
     * Добавление новой записи
     *
     * @param typeRailwayCarriage добавляемая запись
     *
     * @return добавленная запись
     */
    public TypeRailwayCarriage save(TypeRailwayCarriage typeRailwayCarriage) {
        return typeRailwayCarriageRepository.save(typeRailwayCarriage);
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
        return typeRailwayCarriageRepository.existsById(id);
    }

    /**
     * Редактирование записи
     *
     * @param typeRailwayCarriage редактируемая запись
     *
     * @return отредактированная запись
     *
     * @throws NotFountException если записи в базе
     * не существует, то выбрасывается исключение
     */
    public TypeRailwayCarriage update(TypeRailwayCarriage typeRailwayCarriage) throws NotFountException {
        if (typeRailwayCarriage.getTypeRailwayCarriageId() == null
                || !existsById(typeRailwayCarriage.getTypeRailwayCarriageId())) {
            throw new NotFountException();
        }
        return typeRailwayCarriageRepository.save(typeRailwayCarriage);
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
        typeRailwayCarriageRepository.deleteById(id);
    }
}
