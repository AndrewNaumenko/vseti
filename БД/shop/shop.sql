PGDMP         #                x            shop    12.2    12.2                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16393    shop    DATABASE     �   CREATE DATABASE shop WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Russian_Russia.1251' LC_CTYPE = 'Russian_Russia.1251';
    DROP DATABASE shop;
                postgres    false            �            1259    16395 	   customers    TABLE     q  CREATE TABLE public.customers (
    email character varying(255) NOT NULL,
    age integer NOT NULL,
    login character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    patronymic character varying(255) NOT NULL,
    role character varying(255) NOT NULL,
    surname character varying(255) NOT NULL
);
    DROP TABLE public.customers;
       public         heap    postgres    false            �            1259    16419    hibernate_sequence    SEQUENCE     {   CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hibernate_sequence;
       public          postgres    false            �            1259    16403    order_items    TABLE     ?  CREATE TABLE public.order_items (
    order_item_id bigint NOT NULL,
    category character varying(255) NOT NULL,
    description character varying(255) NOT NULL,
    photo character varying(255) NOT NULL,
    price double precision NOT NULL,
    title character varying(255) NOT NULL,
    order_id bigint NOT NULL
);
    DROP TABLE public.order_items;
       public         heap    postgres    false            �            1259    16411    orders    TABLE     �  CREATE TABLE public.orders (
    order_id bigint NOT NULL,
    contact_number character varying(255) NOT NULL,
    delivery_address character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    order_status character varying(255) NOT NULL,
    payment_status character varying(255) NOT NULL,
    payment_type character varying(255) NOT NULL,
    order_time timestamp without time zone NOT NULL
);
    DROP TABLE public.orders;
       public         heap    postgres    false                      0    16395 	   customers 
   TABLE DATA           a   COPY public.customers (email, age, login, name, password, patronymic, role, surname) FROM stdin;
    public          postgres    false    202   �                 0    16403    order_items 
   TABLE DATA           j   COPY public.order_items (order_item_id, category, description, photo, price, title, order_id) FROM stdin;
    public          postgres    false    203                    0    16411    orders 
   TABLE DATA           �   COPY public.orders (order_id, contact_number, delivery_address, email, order_status, payment_status, payment_type, order_time) FROM stdin;
    public          postgres    false    204   )                  0    0    hibernate_sequence    SEQUENCE SET     A   SELECT pg_catalog.setval('public.hibernate_sequence', 1, false);
          public          postgres    false    205            �
           2606    16402    customers customers_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (email);
 B   ALTER TABLE ONLY public.customers DROP CONSTRAINT customers_pkey;
       public            postgres    false    202            �
           2606    16410    order_items order_items_pkey 
   CONSTRAINT     e   ALTER TABLE ONLY public.order_items
    ADD CONSTRAINT order_items_pkey PRIMARY KEY (order_item_id);
 F   ALTER TABLE ONLY public.order_items DROP CONSTRAINT order_items_pkey;
       public            postgres    false    203            �
           2606    16418    orders orders_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (order_id);
 <   ALTER TABLE ONLY public.orders DROP CONSTRAINT orders_pkey;
       public            postgres    false    204            �
           2606    16421 '   order_items fkbioxgbv59vetrxe0ejfubep1w    FK CONSTRAINT     �   ALTER TABLE ONLY public.order_items
    ADD CONSTRAINT fkbioxgbv59vetrxe0ejfubep1w FOREIGN KEY (order_id) REFERENCES public.orders(order_id);
 Q   ALTER TABLE ONLY public.order_items DROP CONSTRAINT fkbioxgbv59vetrxe0ejfubep1w;
       public          postgres    false    203    204    2703               -   x�KOOwH�M���K���4��LII�4�N��`� �W� &|-            x������ � �            x������ � �     