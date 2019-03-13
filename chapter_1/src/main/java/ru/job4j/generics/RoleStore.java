package ru.job4j.generics;

public class RoleStore implements Store<Role> {

    private SimpleArray <Role> roleArray;

    public RoleStore(int cells) {
        this.roleArray = new SimpleArray<Role>(cells);
    }

    @Override
    public void add(Role model) {
        this.roleArray.add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
        boolean result = false;
        Role temp = findById(id);
        if (temp != null) {
            this.roleArray.set(indexOf(id), model);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        if (findById(id) != null) {
            this.roleArray.remove(indexOf(id));
            result = true;
        }
        return result;
    }

    @Override
    public Role findById(String id) {
        Role result = null;
        for (Role role: roleArray
        ) {
            if (id.equals(role.getId())) {
                result = role;
                break;
            }
        }
        return result;
    }

    public int indexOf(String id) {
        int result = -1;
        for (int i = 0; i < this.roleArray.getIndex(); i++) {
            if (this.roleArray.get(i).getId().equals(id)) {
                result = i;
                break;
            }
        }
        return result;
    }
}
