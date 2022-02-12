package io.agus.learning.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class SupplierData {
    @NotEmpty(message = "Nama harus diisi!")
    private String name;

    @NotEmpty(message = "Alamat harus diisi!")
    private String address;

    @NotEmpty(message = "Email harus diisi!")
    @Email(message = "Email tidak valid!")
    private String email;

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
