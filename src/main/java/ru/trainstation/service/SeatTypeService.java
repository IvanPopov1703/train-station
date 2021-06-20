package ru.trainstation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.trainstation.data.entity.SeatType;
import ru.trainstation.data.repository.SeatTypeRepository;
import ru.trainstation.data.repository.SeatRepository;
import ru.trainstation.exception.NotFountException;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatTypeService {

    private final SeatTypeRepository seatRepository;

    @Autowired
    public SeatTypeService(SeatTypeRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    /**
     * Метод для получения всех записей
     *
     * @return список всех существующих записей
     */
    public List<SeatType> getAllTypesOfSeats() {
        var result = new ArrayList<SeatType>();
        seatRepository.findAll().forEach(result::add);
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
    public SeatType findById(Long id) throws NotFountException {
        return seatRepository.findById(id).orElseThrow(NotFountException::new);
    }

    /**
     * Добавление новой записи
     *
     * @param seatType добавляемая запись
     *
     * @return добавленная запись
     */
    public SeatType save(SeatType seatType) {
        return seatRepository.save(seatType);
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
        return seatRepository.existsById(id);
    }

    /**
     * Редактирование записи
     *
     * @param seatType редактируемая запись
     *
     * @return отредактированная запись
     *
     * @throws NotFountException если записи в базе
     * не существует, то выбрасывается исключение
     */
    public SeatType update(SeatType seatType) throws NotFountException {
        if (seatType.getSeatTypeId() == null || !existsById(seatType.getSeatTypeId())) {
            throw new NotFountException();
        }
        return seatRepository.save(seatType);
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
        seatRepository.deleteById(id);
    }
}
