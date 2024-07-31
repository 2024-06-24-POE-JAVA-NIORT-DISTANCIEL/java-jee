package com.bigcorp.booking.jsf;

import com.bigcorp.booking.service.ClientService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Collection;

@Named
@RequestScoped
public class ClientFaceBean {

    private Integer id;
    private ClientFormBean clientFormBean = new ClientFormBean();

    @Inject
    private ClientService clientService;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClientFormBean getClientFormBean() {
        return clientFormBean;
    }

    public void setClientFormBean(ClientFormBean clientFormBean) {
        this.clientFormBean = clientFormBean;
    }

    public Collection<ClientFormBean> getClient() {
        return clientService.findAll();
    }

    public void onLoad() {
        if (id != null) {
            this.clientFormBean = clientService.load(id);
        }
    }

    public String save() {
        clientService.save(clientFormBean);
        return "tableau-clients?faces-redirect=true";
    }

    public String cancel() {
        return "tableau-clients?faces-redirect=true";
    }
}

