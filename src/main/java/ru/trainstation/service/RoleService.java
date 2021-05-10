package ru.trainstation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.trainstation.data.entity.Role;
import ru.trainstation.data.repository.RoleRepository;
import ru.trainstation.exception.NotFountException;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Метод для получения всех записей
     *
     * @return список всех существующих записей
     */
    public List<Role> getAllRoles() {
        var result = new ArrayList<Role>();
        roleRepository.findAll().forEach(result::add);
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
    public Role findById(Long id) throws NotFountException {
        return roleRepository.findById(id).orElseThrow(NotFountException::new);
    }

    /**
     * Добавление новой записи
     *
     * @param role добавляемая запись
     *
     * @return добавленная запись
     */
    public Role save(Role role) {
        return roleRepository.save(role);
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
        return roleRepository.existsById(id);
    }

    /**
     * Редактирование записи
     *
     * @param role редактируемая запись
     *
     * @return отредактированная запись
     *
     * @throws NotFountException если записи в базе
     * не существует, то выбрасывается исключение
     */
    public Role update(Role role) throws NotFountException {
        if (role.getRoleId() == null || !existsById(role.getRoleId())) {
            throw new NotFountException();
        }
        return roleRepository.save(role);
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
        roleRepository.deleteById(id);
    }
}
