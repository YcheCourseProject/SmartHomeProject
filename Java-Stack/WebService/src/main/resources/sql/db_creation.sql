/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2015/7/15 13:43:28                           */
/*==============================================================*/


drop table if exists activity_type;

drop table if exists air_condition;

drop table if exists air_condition_control_detail;

drop table if exists air_condition_demand;

drop table if exists air_condition_real_time_decision;

drop table if exists air_condition_status;

drop table if exists box;

drop table if exists circuit_line;

drop table if exists curtain;

drop table if exists curtain_status;

drop table if exists daily_electricity_price;

drop table if exists electricity_info;

drop table if exists electricity_meter;

drop table if exists electricity_type;

drop table if exists flame_sensor;

drop table if exists flame_sensor_data;

drop table if exists gas_sensor;

drop table if exists gas_sensor_data;

drop table if exists gps_info;

drop table if exists gps_info_to_demand;

drop table if exists has_air_condition_demand;

drop table if exists has_water_heater_demand;

drop table if exists lamp;

drop table if exists lamp_status;

drop table if exists light_sensor;

drop table if exists light_sensor_data;

drop table if exists location_type;

drop table if exists moving_status;

drop table if exists plr_sensor;

drop table if exists plr_sensor_data;

drop table if exists real_time_decision;

drop table if exists real_time_demand;

drop table if exists room;

drop table if exists she_switch;

drop table if exists she_switch_status;

drop table if exists social_activity_to_demand;

drop table if exists social_info;

drop table if exists social_source;

drop table if exists temperature_sensor;

drop table if exists temperature_sensor_data;

drop table if exists user_address;

drop table if exists water_heater;

drop table if exists water_heater_control_detail;

drop table if exists water_heater_demand;

drop table if exists water_heater_real_time_decision;

drop table if exists water_heater_status;

drop table if exists wearable_device_info;

drop table if exists wearable_info_to_demand;

/*==============================================================*/
/* Table: activity_type                                         */
/*==============================================================*/
create table activity_type
(
   activity_type_id     int not null auto_increment,
   activity_type        varchar(15) not null,
   primary key (activity_type_id)
);

/*==============================================================*/
/* Table: air_condition                                         */
/*==============================================================*/
create table air_condition
(
   air_condition_id     int not null auto_increment,
   box_id               int,
   air_condition_ip     varchar(15),
   air_condition_rated_power float,
   primary key (air_condition_id)
);

/*==============================================================*/
/* Table: air_condition_control_detail                          */
/*==============================================================*/
create table air_condition_control_detail
(
   ari_condition_control_id int not null auto_increment,
   air_condition_real_time_decision_id int,
   air_condition_id     int,
   air_condition_control_start_time timestamp not null,
   air_condition_control_end_time timestamp not null,
   air_condition_temperature float not null,
   air_condition_mode   int not null,
   air_condition_control_record_time timestamp not null,
   primary key (ari_condition_control_id)
);

/*==============================================================*/
/* Table: air_condition_demand                                  */
/*==============================================================*/
create table air_condition_demand
(
   air_condition_demand_id int not null auto_increment,
   social_activity_to_demand_id int,
   gps_info_to_demand_id int,
   wearable_info_to_demand_id int,
   air_condition_run_time timestamp,
   air_condtion_stop_time timestamp,
   air_condition_temperature float not null,
   air_condition_temperature_delta float,
   air_condition_demand_internal float,
   primary key (air_condition_demand_id)
);

/*==============================================================*/
/* Table: air_condition_real_time_decision                      */
/*==============================================================*/
create table air_condition_real_time_decision
(
   air_condition_real_time_decision_id int not null auto_increment,
   real_time_decision_id int,
   ari_condition_control_id int,
   air_condition_id     int,
   air_condition_decide_start_time timestamp not null,
   air_condition_decide_end_time timestamp not null,
   air_condition_decide_average_energy float not null,
   air_condition_decision_record_time timestamp not null,
   primary key (air_condition_real_time_decision_id)
);

/*==============================================================*/
/* Table: air_condition_status                                  */
/*==============================================================*/
create table air_condition_status
(
   air_condition_status_id int not null auto_increment,
   air_condition_id     int,
   air_condition_temperature float not null,
   air_condition_mode   int not null,
   is_controlled_by_user bool not null,
   air_condition_status_record_time timestamp not null,
   primary key (air_condition_status_id)
);

/*==============================================================*/
/* Table: box                                                   */
/*==============================================================*/
create table box
(
   box_id               int not null auto_increment,
   room_id              int,
   development_board_ip text not null,
   control_model_ip     text not null,
   primary key (box_id)
);

/*==============================================================*/
/* Table: circuit_line                                          */
/*==============================================================*/
create table circuit_line
(
   circuit_line_id      int not null auto_increment,
   cir_circuit_line_id  int,
   electricity_meter_id int,
   room_id              int,
   circuit_line_description text,
   primary key (circuit_line_id)
);

/*==============================================================*/
/* Table: curtain                                               */
/*==============================================================*/
create table curtain
(
   curtain_id           int not null auto_increment,
   box_id               int,
   curtain_ip           varchar(15),
   curtain_size         float,
   curtain_rated_power  float,
   primary key (curtain_id)
);

/*==============================================================*/
/* Table: curtain_status                                        */
/*==============================================================*/
create table curtain_status
(
   curtain_status_id    int not null auto_increment,
   curtain_id           int,
   curtain_status       float not null,
   is_controlled_by_user bool not null,
   curtain_status_record_time timestamp not null,
   primary key (curtain_status_id)
);

/*==============================================================*/
/* Table: daily_electricity_price                               */
/*==============================================================*/
create table daily_electricity_price
(
   daily_electricity_price_id int not null auto_increment,
   date                 date,
   price_period_0       float,
   price_period_1       float,
   price_period_2       float,
   price_period_3       float,
   price_period_4       float,
   price_period_5       float,
   price_period_6       float,
   price_period_7       float,
   price_period_8       float,
   price_period_9       float,
   price_period_10      float,
   price_period_11      float,
   price_period_12      float,
   price_period_13      float,
   pirce_period_14      float,
   price_period_15      float,
   price_period_16      float,
   price_period_17      float,
   price_period_18      float,
   price_period_19      float,
   price_period_20      float,
   price_period_21      float,
   price_period_22      float,
   price_period_23      float,
   primary key (daily_electricity_price_id)
);

/*==============================================================*/
/* Table: electricity_info                                      */
/*==============================================================*/
create table electricity_info
(
   electricity_info_id  int not null auto_increment,
   electricity_meter_id int,
   active_power         float not null,
   reactive_power       float not null,
   total_consume_energy float not null,
   electricity_info_collect_time timestamp not null,
   primary key (electricity_info_id)
);

/*==============================================================*/
/* Table: electricity_meter                                     */
/*==============================================================*/
create table electricity_meter
(
   electricity_meter_id int not null auto_increment,
   electricity_type_id  int,
   circuit_line_id      int,
   electricity_meter_ip varchar(15),
   primary key (electricity_meter_id)
);

/*==============================================================*/
/* Table: electricity_type                                      */
/*==============================================================*/
create table electricity_type
(
   electricity_type_id  int not null auto_increment,
   electricity_type     text not null,
   primary key (electricity_type_id)
);

/*==============================================================*/
/* Table: flame_sensor                                          */
/*==============================================================*/
create table flame_sensor
(
   flame_sensor_id      int not null auto_increment,
   box_id               int,
   primary key (flame_sensor_id)
);

/*==============================================================*/
/* Table: flame_sensor_data                                     */
/*==============================================================*/
create table flame_sensor_data
(
   flame_data_id        int not null auto_increment,
   flame_sensor_id      int,
   flame_data           float not null,
   flame_data_collect_time timestamp not null,
   primary key (flame_data_id)
);

/*==============================================================*/
/* Table: gas_sensor                                            */
/*==============================================================*/
create table gas_sensor
(
   gas_sensor_id        int not null auto_increment,
   box_id               int,
   primary key (gas_sensor_id)
);

/*==============================================================*/
/* Table: gas_sensor_data                                       */
/*==============================================================*/
create table gas_sensor_data
(
   gas_data_id          int not null auto_increment,
   gas_sensor_id        int,
   gas_data             float not null,
   gas_data_collect_time timestamp not null,
   primary key (gas_data_id)
);

/*==============================================================*/
/* Table: gps_info                                              */
/*==============================================================*/
create table gps_info
(
   gps_id               int not null auto_increment,
   loc_type_id          int,
   gps_longitude        double not null,
   gps_latitude         double not null,
   distance_from_home   float not null,
   gps_record_time      timestamp not null,
   primary key (gps_id)
);

/*==============================================================*/
/* Table: gps_info_to_demand                                    */
/*==============================================================*/
create table gps_info_to_demand
(
   gps_info_to_demand_id int not null auto_increment,
   loc_type_id          int,
   distance_from_home   float,
   primary key (gps_info_to_demand_id)
);

/*==============================================================*/
/* Table: has_air_condition_demand                              */
/*==============================================================*/
create table has_air_condition_demand
(
   air_condition_demand_id int not null auto_increment,
   real_time_demand_id  int not null,
   primary key (air_condition_demand_id, real_time_demand_id)
);

/*==============================================================*/
/* Table: has_water_heater_demand                               */
/*==============================================================*/
create table has_water_heater_demand
(
   water_heater_demand_id int not null auto_increment,
   real_time_demand_id  int not null,
   primary key (water_heater_demand_id, real_time_demand_id)
);

/*==============================================================*/
/* Table: lamp                                                  */
/*==============================================================*/
create table lamp
(
   lamp_id              int not null auto_increment,
   box_id               int,
   lamp_type            int,
   lamp_rated_power     float,
   lamp_location        int,
   primary key (lamp_id)
);

/*==============================================================*/
/* Table: lamp_status                                           */
/*==============================================================*/
create table lamp_status
(
   lamp_status_id       int not null auto_increment,
   lamp_id              int,
   lamp_status          int not null,
   is_controlled_by_user bool not null,
   lamp_status_record_time timestamp not null,
   primary key (lamp_status_id)
);

/*==============================================================*/
/* Table: light_sensor                                          */
/*==============================================================*/
create table light_sensor
(
   light_sensor_id      int not null auto_increment,
   box_id               int,
   primary key (light_sensor_id)
);

/*==============================================================*/
/* Table: light_sensor_data                                     */
/*==============================================================*/
create table light_sensor_data
(
   light_data_id        int not null auto_increment,
   light_sensor_id      int,
   light_data           float not null,
   light_data_collect_time timestamp not null,
   primary key (light_data_id)
);

/*==============================================================*/
/* Table: location_type                                         */
/*==============================================================*/
create table location_type
(
   loc_type_id          int not null auto_increment,
   loc_type             varchar(15) not null,
   primary key (loc_type_id)
);

/*==============================================================*/
/* Table: moving_status                                         */
/*==============================================================*/
create table moving_status
(
   moving_type_id       int not null auto_increment,
   moving_type          varchar(15) not null,
   primary key (moving_type_id)
);

/*==============================================================*/
/* Table: plr_sensor                                            */
/*==============================================================*/
create table plr_sensor
(
   plr_sensor_id        int not null auto_increment,
   box_id               int,
   primary key (plr_sensor_id)
);

/*==============================================================*/
/* Table: plr_sensor_data                                       */
/*==============================================================*/
create table plr_sensor_data
(
   plr_data_id          int not null auto_increment,
   plr_sensor_id        int,
   plr_data             bool not null,
   plr_data_collect_time timestamp not null,
   primary key (plr_data_id)
);

/*==============================================================*/
/* Table: real_time_decision                                    */
/*==============================================================*/
create table real_time_decision
(
   real_time_decision_id int not null auto_increment,
   real_time_demand_id  int,
   real_time_decision_record_time timestamp not null,
   primary key (real_time_decision_id)
);

/*==============================================================*/
/* Table: real_time_demand                                      */
/*==============================================================*/
create table real_time_demand
(
   real_time_demand_id  int not null auto_increment,
   real_time_demand_record_time timestamp not null,
   primary key (real_time_demand_id)
);

/*==============================================================*/
/* Table: room                                                  */
/*==============================================================*/
create table room
(
   room_id              int not null auto_increment,
   room_size            float,
   primary key (room_id)
);

/*==============================================================*/
/* Table: she_switch                                            */
/*==============================================================*/
create table she_switch
(
   she_switch_id        int not null auto_increment,
   primary key (she_switch_id)
);

/*==============================================================*/
/* Table: she_switch_status                                     */
/*==============================================================*/
create table she_switch_status
(
   she_switch_status_id int not null auto_increment,
   she_switch_id        int,
   she_switch_status    bool not null,
   is_controlled_by_user bool,
   she_switch_status_record_time timestamp not null,
   primary key (she_switch_status_id)
);

/*==============================================================*/
/* Table: social_activity_to_demand                             */
/*==============================================================*/
create table social_activity_to_demand
(
   social_activity_to_demand_id int not null auto_increment,
   activity_type_id     int,
   social_activity_begin_time varchar(20),
   primary key (social_activity_to_demand_id)
);

/*==============================================================*/
/* Table: social_info                                           */
/*==============================================================*/
create table social_info
(
   social_info_id       int not null auto_increment,
   activity_type_id     int,
   source_type_id       int,
   start_time           varchar(6),
   end_time             varchar(6),
   location             varchar(10),
   activity_sent_time   timestamp,
   activity_record_time timestamp not null,
   primary key (social_info_id)
);

/*==============================================================*/
/* Table: social_source                                         */
/*==============================================================*/
create table social_source
(
   source_type_id       int not null auto_increment,
   source_type          text not null,
   primary key (source_type_id)
);

/*==============================================================*/
/* Table: temperature_sensor                                    */
/*==============================================================*/
create table temperature_sensor
(
   temperature_sensor_id int not null auto_increment,
   box_id               int,
   primary key (temperature_sensor_id)
);

/*==============================================================*/
/* Table: temperature_sensor_data                               */
/*==============================================================*/
create table temperature_sensor_data
(
   temperature_data_id  int not null auto_increment,
   temperature_sensor_id int,
   temperature_data     float not null,
   temperature_data_collect_time timestamp not null,
   primary key (temperature_data_id)
);

/*==============================================================*/
/* Table: user_address                                          */
/*==============================================================*/
create table user_address
(
   user_address_id      int not null auto_increment,
   user_address_latitude float not null,
   user_address_longitude float not null,
   user_address_detail  text not null,
   primary key (user_address_id)
);

/*==============================================================*/
/* Table: water_heater                                          */
/*==============================================================*/
create table water_heater
(
   water_heater_id      int not null auto_increment,
   box_id               int,
   water_heater_rated_power float,
   primary key (water_heater_id)
);

/*==============================================================*/
/* Table: water_heater_control_detail                           */
/*==============================================================*/
create table water_heater_control_detail
(
   water_heater_control_id int not null auto_increment,
   water_heater_id      int,
   water_heater_real_time_decision_id int,
   water_heater_start_time timestamp not null,
   water_heater_end_time timestamp not null,
   water_temperature    float not null,
   water_temperature_decision_time timestamp not null,
   primary key (water_heater_control_id)
);

/*==============================================================*/
/* Table: water_heater_demand                                   */
/*==============================================================*/
create table water_heater_demand
(
   water_heater_demand_id int not null auto_increment,
   wearable_info_to_demand_id int,
   gps_info_to_demand_id int,
   social_activity_to_demand_id int,
   water_heater_run_time timestamp,
   water_heater_stop_time timestamp,
   water_heater_temperature float,
   water_heater_temperature_delta float,
   water_heater_demand_internal float,
   primary key (water_heater_demand_id)
);

/*==============================================================*/
/* Table: water_heater_real_time_decision                       */
/*==============================================================*/
create table water_heater_real_time_decision
(
   water_heater_real_time_decision_id int not null auto_increment,
   water_heater_id      int,
   real_time_decision_id int,
   water_heater_control_id int,
   water_heater_real_start_time timestamp,
   water_heater_real_end_time timestamp,
   water_heater_consume_average_energy float,
   water_heater_decision_record_time timestamp,
   primary key (water_heater_real_time_decision_id)
);

/*==============================================================*/
/* Table: water_heater_status                                   */
/*==============================================================*/
create table water_heater_status
(
   water_heater_stauts_id int not null auto_increment,
   water_heater_id      int,
   water_heater_temperature float not null,
   is_controlled_by_user bool,
   water_heater_status_record_time timestamp not null,
   primary key (water_heater_stauts_id)
);

/*==============================================================*/
/* Table: wearable_device_info                                  */
/*==============================================================*/
create table wearable_device_info
(
   wearable_info_id     int not null auto_increment,
   moving_type_id       int,
   accelerated_speed_x  float,
   accelerated_speed_y  float,
   accelerated_speed_z  float,
   gyroscope_x          float,
   gyroscope_y          float,
   gyroscope_z          float,
   body_temperature     float not null,
   heart_rate           float,
   speed                float,
   wearable_info_record_time timestamp,
   primary key (wearable_info_id)
);

/*==============================================================*/
/* Table: wearable_info_to_demand                               */
/*==============================================================*/
create table wearable_info_to_demand
(
   wearable_info_to_demand_id int not null auto_increment,
   moving_type_id       int,
   heart_rate           float not null,
   body_temperature     float not null,
   primary key (wearable_info_to_demand_id)
);

alter table air_condition add constraint FK_control_air_condition foreign key (box_id)
      references box (box_id) on delete restrict on update restrict;

alter table air_condition_control_detail add constraint FK_air_condtion_has_control_detail foreign key (air_condition_id)
      references air_condition (air_condition_id) on delete restrict on update restrict;

alter table air_condition_control_detail add constraint FK_transfer_to_air_condition_control_detail foreign key (air_condition_real_time_decision_id)
      references air_condition_real_time_decision (air_condition_real_time_decision_id) on delete restrict on update restrict;

alter table air_condition_demand add constraint FK_gps_info_has_air_condition_demand_rule foreign key (gps_info_to_demand_id)
      references gps_info_to_demand (gps_info_to_demand_id) on delete restrict on update restrict;

alter table air_condition_demand add constraint FK_social_activity_has_air_condition_demand_rule foreign key (social_activity_to_demand_id)
      references social_activity_to_demand (social_activity_to_demand_id) on delete restrict on update restrict;

alter table air_condition_demand add constraint FK_wearable_info_has_air_condition_demand_rule foreign key (wearable_info_to_demand_id)
      references wearable_info_to_demand (wearable_info_to_demand_id) on delete restrict on update restrict;

alter table air_condition_real_time_decision add constraint FK_air_condition_has_decision_detail foreign key (air_condition_id)
      references air_condition (air_condition_id) on delete restrict on update restrict;

alter table air_condition_real_time_decision add constraint FK_has_air_condition_real_time_desicion foreign key (real_time_decision_id)
      references real_time_decision (real_time_decision_id) on delete restrict on update restrict;

alter table air_condition_real_time_decision add constraint FK_transfer_to_air_condition_control_detail2 foreign key (ari_condition_control_id)
      references air_condition_control_detail (ari_condition_control_id) on delete restrict on update restrict;

alter table air_condition_status add constraint FK_record_air_condition_status foreign key (air_condition_id)
      references air_condition (air_condition_id) on delete restrict on update restrict;

alter table box add constraint FK_belong_to foreign key (room_id)
      references room (room_id) on delete restrict on update restrict;

alter table circuit_line add constraint FK_has_circuit_line foreign key (room_id)
      references room (room_id) on delete restrict on update restrict;

alter table circuit_line add constraint FK_has_electricity_meter foreign key (electricity_meter_id)
      references electricity_meter (electricity_meter_id) on delete restrict on update restrict;

alter table circuit_line add constraint FK_has_father_circuit_line foreign key (cir_circuit_line_id)
      references circuit_line (circuit_line_id) on delete restrict on update restrict;

alter table curtain add constraint FK_control_curtain foreign key (box_id)
      references box (box_id) on delete restrict on update restrict;

alter table curtain_status add constraint FK_record_curtain_status foreign key (curtain_id)
      references curtain (curtain_id) on delete restrict on update restrict;

alter table electricity_info add constraint FK_collect_electricity_meter_data foreign key (electricity_meter_id)
      references electricity_meter (electricity_meter_id) on delete restrict on update restrict;

alter table electricity_meter add constraint FK_has_electricity_meter2 foreign key (circuit_line_id)
      references circuit_line (circuit_line_id) on delete restrict on update restrict;

alter table electricity_meter add constraint FK_refer foreign key (electricity_type_id)
      references electricity_type (electricity_type_id) on delete restrict on update restrict;

alter table flame_sensor add constraint FK_has_flame_sensor foreign key (box_id)
      references box (box_id) on delete restrict on update restrict;

alter table flame_sensor_data add constraint FK_collect_flame_data foreign key (flame_sensor_id)
      references flame_sensor (flame_sensor_id) on delete restrict on update restrict;

alter table gas_sensor add constraint FK_has_gas_sensor foreign key (box_id)
      references box (box_id) on delete restrict on update restrict;

alter table gas_sensor_data add constraint FK_collect_gas_data foreign key (gas_sensor_id)
      references gas_sensor (gas_sensor_id) on delete restrict on update restrict;

alter table gps_info add constraint FK_reflect_a_location_type foreign key (loc_type_id)
      references location_type (loc_type_id) on delete restrict on update restrict;

alter table gps_info_to_demand add constraint FK_location_type_has_demand_rule foreign key (loc_type_id)
      references location_type (loc_type_id) on delete restrict on update restrict;

alter table has_air_condition_demand add constraint FK_has_air_condition_demand foreign key (air_condition_demand_id)
      references air_condition_demand (air_condition_demand_id) on delete restrict on update restrict;

alter table has_air_condition_demand add constraint FK_has_air_condition_demand2 foreign key (real_time_demand_id)
      references real_time_demand (real_time_demand_id) on delete restrict on update restrict;

alter table has_water_heater_demand add constraint FK_has_water_heater_demand foreign key (water_heater_demand_id)
      references water_heater_demand (water_heater_demand_id) on delete restrict on update restrict;

alter table has_water_heater_demand add constraint FK_has_water_heater_demand2 foreign key (real_time_demand_id)
      references real_time_demand (real_time_demand_id) on delete restrict on update restrict;

alter table lamp add constraint FK_control_lamp foreign key (box_id)
      references box (box_id) on delete restrict on update restrict;

alter table lamp_status add constraint FK_record_lamp_status foreign key (lamp_id)
      references lamp (lamp_id) on delete restrict on update restrict;

alter table light_sensor add constraint FK_has_light_sensor foreign key (box_id)
      references box (box_id) on delete restrict on update restrict;

alter table light_sensor_data add constraint FK_collect_light_data foreign key (light_sensor_id)
      references light_sensor (light_sensor_id) on delete restrict on update restrict;

alter table plr_sensor add constraint FK_has_plr_sensor foreign key (box_id)
      references box (box_id) on delete restrict on update restrict;

alter table plr_sensor_data add constraint FK_collect_plr_data foreign key (plr_sensor_id)
      references plr_sensor (plr_sensor_id) on delete restrict on update restrict;

alter table real_time_decision add constraint FK_operate foreign key (real_time_demand_id)
      references real_time_demand (real_time_demand_id) on delete restrict on update restrict;

alter table she_switch_status add constraint FK_record_switch_hstatus foreign key (she_switch_id)
      references she_switch (she_switch_id) on delete restrict on update restrict;

alter table social_activity_to_demand add constraint FK_activity_type_has_demand_rule foreign key (activity_type_id)
      references activity_type (activity_type_id) on delete restrict on update restrict;

alter table social_info add constraint FK_collected_from foreign key (source_type_id)
      references social_source (source_type_id) on delete restrict on update restrict;

alter table social_info add constraint FK_reflect_activities foreign key (activity_type_id)
      references activity_type (activity_type_id) on delete restrict on update restrict;

alter table temperature_sensor add constraint FK_has_temperature_sensor foreign key (box_id)
      references box (box_id) on delete restrict on update restrict;

alter table temperature_sensor_data add constraint FK_collect_temperature_data foreign key (temperature_sensor_id)
      references temperature_sensor (temperature_sensor_id) on delete restrict on update restrict;

alter table water_heater add constraint FK_contro_water_heater foreign key (box_id)
      references box (box_id) on delete restrict on update restrict;

alter table water_heater_control_detail add constraint FK_transfer_to_water_heater_control_detail2 foreign key (water_heater_real_time_decision_id)
      references water_heater_real_time_decision (water_heater_real_time_decision_id) on delete restrict on update restrict;

alter table water_heater_control_detail add constraint FK_water_heater_has_control_detail foreign key (water_heater_id)
      references water_heater (water_heater_id) on delete restrict on update restrict;

alter table water_heater_demand add constraint FK_gps_info_has_water_heater_demand_rule foreign key (gps_info_to_demand_id)
      references gps_info_to_demand (gps_info_to_demand_id) on delete restrict on update restrict;

alter table water_heater_demand add constraint FK_social_activity_has_water_heater_demand_rule foreign key (social_activity_to_demand_id)
      references social_activity_to_demand (social_activity_to_demand_id) on delete restrict on update restrict;

alter table water_heater_demand add constraint FK_wearable_info_has_water_heater_demand_rule foreign key (wearable_info_to_demand_id)
      references wearable_info_to_demand (wearable_info_to_demand_id) on delete restrict on update restrict;

alter table water_heater_real_time_decision add constraint FK_has_water_heater_real_time_decision foreign key (real_time_decision_id)
      references real_time_decision (real_time_decision_id) on delete restrict on update restrict;

alter table water_heater_real_time_decision add constraint FK_transfer_to_water_heater_control_detail foreign key (water_heater_control_id)
      references water_heater_control_detail (water_heater_control_id) on delete restrict on update restrict;

alter table water_heater_real_time_decision add constraint FK_water_heater_has_decision_detail foreign key (water_heater_id)
      references water_heater (water_heater_id) on delete restrict on update restrict;

alter table water_heater_status add constraint FK_record_water_heater_status foreign key (water_heater_id)
      references water_heater (water_heater_id) on delete restrict on update restrict;

alter table wearable_device_info add constraint FK_reflect_a_moving_status foreign key (moving_type_id)
      references moving_status (moving_type_id) on delete restrict on update restrict;

alter table wearable_info_to_demand add constraint FK_moving_status_has_demand_rule foreign key (moving_type_id)
      references moving_status (moving_type_id) on delete restrict on update restrict;

