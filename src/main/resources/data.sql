insert into gpa(balance) values(800.00);
insert into gpa(balance) values(10000.00);
insert into gpa(balance) values(100.00);

insert into app_user(gpa_id, user_name) values(1, 'Aslıhan Yüksel');
insert into app_user(gpa_id, user_name) values(2, 'Bulut Yüksel');


insert into card(gpa_id, card_number, cvv, expiry, state, mcc_list) values ( 1, '1111111111', '245', '10/25', 'A', 'A,B,C,D' );
insert into card(gpa_id, card_number, cvv, expiry, state, mcc_list) values ( 2, '2222222222', '266', '10/25', 'A', 'X,Y,Z' );


insert into merchant(mcc, merchant_name, gpa_id) values('Z', 'TEST MERCHANT', 3);