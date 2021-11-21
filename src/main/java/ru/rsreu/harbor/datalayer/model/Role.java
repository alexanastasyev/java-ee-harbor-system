package ru.rsreu.harbor.datalayer.model;

public class Role {
    private final Long id;
    private final String title;

    public Role(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof Role)) {
            return false;
        }
        return this.getTitle().equals(((Role) obj).getTitle());
    }
}
