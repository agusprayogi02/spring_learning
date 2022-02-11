package io.agus.learning.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class SupplierData {
    @NotEmpty(message = "Nama harus diisi!")
    private String name;

    @NotEmpty(message = "Alamat harus diisi!")
    private String address;

    @Email(message = "Email tidak valid!")
    @NotEmpty(message = "Email harus diisi!")
    private String email;

    public SupplierData(String name, String address, String email) {
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public SupplierData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
