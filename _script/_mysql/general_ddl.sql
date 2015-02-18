
    alter table general_address 
        drop 
        foreign key FK_rqngw4hffbhsd9ggwj3402sf2;

    alter table general_user_property 
        drop 
        foreign key FK_t1cu43w80mi1gagr6sxpyqx3k;

    alter table rel_application_ip 
        drop 
        foreign key FK_c8ifp07qv3d52i61bt02mh8us;

    alter table rel_application_mac 
        drop 
        foreign key FK_tib6jp8bb2elcq5dd8m5jqy9u;

    alter table rel_application_properties 
        drop 
        foreign key FK_r1ei799iykkjrnhfu08b93nd;

    drop table if exists general_address;

    drop table if exists general_application;

    drop table if exists general_application_detail;

    drop table if exists general_translation;

    drop table if exists general_user;

    drop table if exists general_user_detail;

    drop table if exists general_user_property;

    drop table if exists rel_application_ip;

    drop table if exists rel_application_mac;

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
        app_pass varchar(255) not null,
        primary key (id)
    );

    create table general_application_detail (
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
        encrypted boolean not null,
        propertyKey varchar(255) not null,
        propertyValue varchar(255) not null,
        user_detail_id bigint,
        primary key (id)
    );

    create table rel_application_ip (
        application_id bigint not null,
        ip varchar(255)
    );

    create table rel_application_mac (
        application_id bigint not null,
        mac varchar(255)
    );

    create table rel_application_properties (
        application_id bigint not null,
        propertyValue varchar(255),
        propertyKey varchar(255) not null,
        primary key (application_id, propertyKey)
    );

    alter table general_application 
        add constraint uk_application_key  unique (app_key);

    alter table general_application_detail 
        add constraint uk_application_key  unique (app_key);

    alter table general_translation 
        add constraint uk_translation_key_lan_app  unique (translationkey, translationlanguage, application);

    alter table general_user_detail 
        add constraint uk_userdetail_username  unique (username);

    alter table rel_application_ip 
        add constraint uk_application_ip  unique (application_id, ip);

    alter table rel_application_mac 
        add constraint uk_application_mac  unique (application_id, mac);

    alter table general_address 
        add constraint FK_rqngw4hffbhsd9ggwj3402sf2 
        foreign key (addresses_id) 
        references general_user_detail (id);

    alter table general_user_property 
        add constraint FK_t1cu43w80mi1gagr6sxpyqx3k 
        foreign key (user_detail_id) 
        references general_user_detail (id);

    alter table rel_application_ip 
        add constraint FK_c8ifp07qv3d52i61bt02mh8us 
        foreign key (application_id) 
        references general_application (id);

    alter table rel_application_mac 
        add constraint FK_tib6jp8bb2elcq5dd8m5jqy9u 
        foreign key (application_id) 
        references general_application (id);

    alter table rel_application_properties 
        add constraint FK_r1ei799iykkjrnhfu08b93nd 
        foreign key (application_id) 
        references general_application_detail (id);

    create table hibernate_sequences (
         sequence_name varchar(255),
         sequence_next_hi_value integer 
    );
