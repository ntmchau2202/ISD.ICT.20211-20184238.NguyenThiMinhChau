create database AIMS;

create table Customer(customer_id int not null identity(1,1) primary key,
						customer_name varchar(256) not null,
						customer_phone varchar(12),
						customer_city varchar(32) not null,
						customer_district varchar(48) not null,
						customer_house varchar(128) not null);

create table CreditCard(creditcard_number varchar(25) not null primary key,
						cardholder_name varchar(48) not null,
						balance float not null,
						security_code varchar(8) not null,
						issuing_bank varchar(128) not null,
						expiration_date date not null,
						constraint Check_Balance check (balance >=0));

create table Media(media_id varchar(4) not null primary key,
					media_title varchar(128) not null,
					media_price float not null,
					media_stock int not null,
					media_rush_support bit not null,
					constraint Check_Price check (media_price >0));

create table Book(book_id varchar(4) not null primary key,
				constraint FK_BookMediaID foreign key (book_id) references Media(media_id));

create table Author(author_id varchar(4) not null primary key,
					author_name varchar(128));

create table BookAuthor(book_id varchar(4) not null,
						author_id varchar(4) not null,
						constraint PK_BookAuthor primary key (book_id, author_id),
						constraint FK_BookBookID foreign key (book_id) references Book(book_id),
						constraint FK_AuthorAuthorID foreign key (author_id) references Author(author_id));

create table Disc(disc_id varchar(4) not null primary key,
					disc_length float not null,
					constraint FK_DiscMediaID foreign key (disc_id) references Media(media_id),
					constraint Check_DiscLength check (disc_length > 0));

create table CD(cd_id varchar(4) not null primary key,
				constraint FK_CDMediaID foreign key (cd_id) references Media(media_id));

create table Track(track_id varchar(4) not null primary key,
					track_length float not null,
					constraint FK_TrackMediaID foreign key (track_id) references Media(media_id),
					constraint Check_TrackLength check (track_length > 0));

create table CDTrack(cd_id varchar(4) not null,
					track_id varchar(4) not null,
					constraint PK_CDTrack primary key (cd_id, track_id),
					constraint FK_CDCDID foreign key (cd_id) references CD(cd_id),
					constraint FK_TrackTrackID foreign key (track_id) references Track(track_id));

create table DVD(dvd_id varchar(4) not null primary key,
				constraint FK_DVDMediaID foreign key (dvd_id) references Media(media_id));

create table Artist(artist_id varchar(4) not null primary key,
					artist_name varchar(128));

create table DiscArtist(disc_id varchar(4) not null,
						artist_id varchar(4) not null,
						constraint PK_DiscArtist primary key (disc_id, artist_id),
						constraint FK_DiscDiscID foreign key (disc_id) references Disc(disc_id),
						constraint FK_ArtistArtistID foreign key (artist_id) references Artist(artist_id));

create table AIMSOrder(order_id int not null primary key,
						customer_id int not null,
						date_placed date not null,
						cost float not null,
						is_rushed bit not null,
						constraint FK_OrderCustomer foreign key (customer_id) references Customer(customer_id),
						constraint Check_Cost check (cost >= 0));

create table OrderDetails(order_id int not null,
							media_id varchar(4) not null,
							quantity int not null,
							constraint PK_OrderMedia primary key (order_id, media_id),
							constraint FK_OrderOrderID foreign key (order_id) references AIMSOrder(order_id),
							constraint FK_MediaMediaID foreign key (media_id) references Media(media_id),
							constraint Check_Quantity check (quantity>0));


create table Cart(media_id varchar(4) not null primary key,
					quantity int not null,								
					constraint FK_CartMediaID foreign key (media_id) references Media(media_id));

create table BankTransaction(transaction_id int not null primary key,
							transaction_detail varchar(256),
							transaction_amount float not null,
							transaction_time datetime not null,
							creditcard_number varchar(25) not null,
							constraint FK_TransactionCard foreign key (creditcard_number) references CreditCard(creditcard_number),
							constraint Check_TransactionAmount check (transaction_amount > 0));

create table Invoice(invoice_id int not null,
					order_id int not null,
					transaction_id int not null,
					constraint FK_InvoiceOrderID foreign key (order_id) references AIMSOrder(order_id),
					constraint FK_InvoiceTransactionID foreign key (transaction_id) references BankTransaction(transaction_id));

