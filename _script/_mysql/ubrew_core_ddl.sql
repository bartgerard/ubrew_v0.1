
    alter table general_address 
        drop 
        foreign key FK_rqngw4hffbhsd9ggwj3402sf2;

    alter table general_user_property 
        drop 
        foreign key FK_t30lmp6kccqor42qg3gaa9nbe;

    alter table rel_application_properties 
        drop 
        foreign key FK_r1ei799iykkjrnhfu08b93nd;

    drop table if exists general_address;

    drop table if exists general_application;

    drop table if exists general_translation;

    drop table if exists general_user;

    drop table if exists general_user_detail;

    drop table if exists general_user_property;

    drop table if exists rel_application_properties;

    drop table if exists hibernate_sequences;

    create table general_address (
        id bigint not null,
        create_ts timestamp not null,
        create_user varchar(255) not null,
        update_ts timestamp not null,
        update_user varchar(255) not null,
        additional varchar(255),
        addressee varchar(255) not null,
        city varchar(255) not null,
        country varchar(255) not null,
        homeAddress boolean not null,
        house_number integer not null,
        preferred boolean not null,
        stateOrProvince varchar(255) not null,
        street varchar(255) not null,
        zipOrPostalCode integer not null,
        addresses_id bigint,
        primary key (id)
    );

    create table general_application (
        id bigint not null,
        create_ts timestamp not null,
        create_user varchar(255) not null,
        update_ts timestamp not null,
        update_user varchar(255) not null,
        app_key varchar(255) not null,
        app_name varchar(255) not null,
        primary key (id)
    );

    create table general_translation (
        id bigint not null,
        create_ts timestamp not null,
        create_user varchar(255) not null,
        update_ts timestamp not null,
        update_user varchar(255) not null,
        application varchar(255) not null,
        translationkey varchar(255) not null,
        translationlanguage varchar(255) not null,
        translationvalue varchar(255) not null,
        primary key (id)
    );

    create table general_user (
        username varchar(255) not null,
        enabled boolean not null,
        password varchar(255) not null,
        primary key (username)
    );

    create table general_user_detail (
        id bigint not null,
        create_ts timestamp not null,
        create_user varchar(255) not null,
        update_ts timestamp not null,
        update_user varchar(255) not null,
        birthdate date not null,
        firstname varchar(255) not null,
        lastname varchar(255) not null,
        username varchar(255) not null,
        primary key (id)
    );

    create table general_user_property (
        id bigint not null,
        application varchar(255),
        propertyKey varchar(255) not null,
        propertyValue varchar(255) not null,
        properties_id bigint,
        primary key (id)
    );

    create table rel_application_properties (
        application_id bigint not null,
        propertyValue varchar(255),
        propertyKey varchar(255) not null,
        primary key (application_id, propertyKey)
    );

    alter table general_application 
        add constraint uk_application_key  unique (app_key);

    alter table general_translation 
        add constraint UK_hhinpnci2t1l54rq9b985yrtl  unique (translationkey, translationlanguage, application);

    alter table general_user_detail 
        add constraint UK_atpg30ktislblv6m61c9pribx  unique (username);

    alter table general_address 
        add constraint FK_rqngw4hffbhsd9ggwj3402sf2 
        foreign key (addresses_id) 
        references general_user_detail (id);

    alter table general_user_property 
        add constraint FK_t30lmp6kccqor42qg3gaa9nbe 
        foreign key (properties_id) 
        references general_user_detail (id);

    alter table rel_application_properties 
        add constraint FK_r1ei799iykkjrnhfu08b93nd 
        foreign key (application_id) 
        references general_application (id);

    create table hibernate_sequences (
         sequence_name varchar(255),
         sequence_next_hi_value integer 
    );
