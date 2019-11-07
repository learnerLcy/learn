package com.learn.PoJo.system;

import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "menu")
@Data
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String menuName;
    private String menuPid;
    private String menuDescription;
    private String menuUrl;
    private String menuIcon;
    private String menuFlag;
    private String menuNum;
    @Transient
    private String menuFlagName;
    @Ignore
    private List<Menu> chrildrenList;
}