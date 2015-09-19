package be.gerard.ubrew.web.controller;

import be.gerard.common.exception_v1.ServiceException;
import be.gerard.common.web.GeneralBean;
import be.gerard.core.interface_v1.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

//@ViewScoped
@Scope("session")
@Named
public class RegisterBean extends GeneralBean<User> implements Serializable {

    private static final Logger logger = LogManager.getLogger(RegisterBean.class.getName());

    private static final long serialVersionUID = 1L;

    private String firstname;

    private String lastname;

    private String username;

    private String password;

    private String passwordVerification;

    private Date birthDate;

    private String address1; // Street address, P.O. box

    private String address2; // Apartment, suite, unit, building, floor, etc.

    private String city;

    private Integer zipOrPostalCode;

    private String stateOrProvince;

    private String country;

//    private final List<Address> addresses = new ArrayList<>();

//    private Address address;

    @Inject
    private LoginBean loginBean;

//    @Autowired
//    private UserService userService;

    @Value("${be.gerard.general.application.name}")
    private String applicationName;

    @Override
    public User getSelected() {
        if (super.getSelected() == null) {
            setSelected(new User());
//            getSelected().add(new Address());
        }
        return super.getSelected();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordVerification() {
        return passwordVerification;
    }

    public void setPasswordVerification(String passwordVerification) {
        this.passwordVerification = passwordVerification;
    }

    public Date getBirthDate() {
        if (birthDate == null) {
            birthDate = new Date();//Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        }
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getZipOrPostalCode() {
        return zipOrPostalCode;
    }

    public void setZipOrPostalCode(Integer zipOrPostalCode) {
        this.zipOrPostalCode = zipOrPostalCode;
    }

    public String getStateOrProvince() {
        return stateOrProvince;
    }

    public void setStateOrProvince(String stateOrProvince) {
        this.stateOrProvince = stateOrProvince;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

 //   public List<Address> getAddresses() {
 //       return addresses;
 //   }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

//   public UserService getUserService() {
//        return userService;
//    }

//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }

    //
    public void register() {
        logger.entry();
        if (!Objects.equals(password, passwordVerification)) {
            FacesContext.getCurrentInstance().addMessage("registrationForm:registrationErrors", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Be advised. The passwords did not match.", ""));
            FacesContext.getCurrentInstance().validationFailed();
        } else {
            try {
                //LocalDate localBirthDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(birthDate.getTime()), ZoneId.systemDefault()).toLocalDate();
                //userService.saveOrUpdate(new User(username, firstname, lastname, birthDate, true, new Address(address1, address2, city, zipOrPostalCode, stateOrProvince, country)), password, applicationName);
                persist(PersistAction.CREATE, username);
                loginBean.login(username, password);
            } catch (ServiceException e) {
                handleException(e);
            }
        }
    }

    @Override
    protected void persist(PersistAction persistAction, String successMessage) {
        if (getSelected() != null) {
            try {
                if (persistAction != PersistAction.DELETE) {
//                    getUserService().save(getSelected(), password, "");
                }
            } catch (ServiceException e) {
                handleException(e);
            }
        }
    }

//    public Address getAddress() {
//        return address;
//    }

}
