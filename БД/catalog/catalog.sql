PGDMP     *    "                x            catalog    12.2    12.2                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16394    catalog    DATABASE     �   CREATE DATABASE catalog WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Russian_Russia.1251' LC_CTYPE = 'Russian_Russia.1251';
    DROP DATABASE catalog;
                postgres    false            �            1259    16426 
   categories    TABLE     r   CREATE TABLE public.categories (
    category_id bigint NOT NULL,
    category character varying(255) NOT NULL
);
    DROP TABLE public.categories;
       public         heap    postgres    false            �            1259    16448    hibernate_sequence    SEQUENCE     {   CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hibernate_sequence;
       public          postgres    false            �            1259    16431    offers    TABLE       CREATE TABLE public.offers (
    offer_id bigint NOT NULL,
    description character varying(255) NOT NULL,
    photo character varying(255) NOT NULL,
    title character varying(255) NOT NULL,
    category_id bigint NOT NULL,
    price_id bigint NOT NULL
);
    DROP TABLE public.offers;
       public         heap    postgres    false            �            1259    16439    prices    TABLE     b   CREATE TABLE public.prices (
    price_id bigint NOT NULL,
    price double precision NOT NULL
);
    DROP TABLE public.prices;
       public         heap    postgres    false                      0    16426 
   categories 
   TABLE DATA           ;   COPY public.categories (category_id, category) FROM stdin;
    public          postgres    false    202   �                 0    16431    offers 
   TABLE DATA           \   COPY public.offers (offer_id, description, photo, title, category_id, price_id) FROM stdin;
    public          postgres    false    203                    0    16439    prices 
   TABLE DATA           1   COPY public.prices (price_id, price) FROM stdin;
    public          postgres    false    204   7                  0    0    hibernate_sequence    SEQUENCE SET     A   SELECT pg_catalog.setval('public.hibernate_sequence', 1, false);
          public          postgres    false    205            �
           2606    16430    categories categories_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (category_id);
 D   ALTER TABLE ONLY public.categories DROP CONSTRAINT categories_pkey;
       public            postgres    false    202            �
           2606    16438    offers offers_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.offers
    ADD CONSTRAINT offers_pkey PRIMARY KEY (offer_id);
 <   ALTER TABLE ONLY public.offers DROP CONSTRAINT offers_pkey;
       public            postgres    false    203            �
           2606    16443    prices prices_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.prices
    ADD CONSTRAINT prices_pkey PRIMARY KEY (price_id);
 <   ALTER TABLE ONLY public.prices DROP CONSTRAINT prices_pkey;
       public            postgres    false    204            �
           2606    16445 '   categories uk_5ky4frjmcobbiayt5jyx53mff 
   CONSTRAINT     f   ALTER TABLE ONLY public.categories
    ADD CONSTRAINT uk_5ky4frjmcobbiayt5jyx53mff UNIQUE (category);
 Q   ALTER TABLE ONLY public.categories DROP CONSTRAINT uk_5ky4frjmcobbiayt5jyx53mff;
       public            postgres    false    202            �
           2606    16447 #   offers uk_ljgjbr2lokb72itihrpem4a95 
   CONSTRAINT     b   ALTER TABLE ONLY public.offers
    ADD CONSTRAINT uk_ljgjbr2lokb72itihrpem4a95 UNIQUE (price_id);
 M   ALTER TABLE ONLY public.offers DROP CONSTRAINT uk_ljgjbr2lokb72itihrpem4a95;
       public            postgres    false    203            �
           2606    16455 "   offers fk941mnejw7k5khxgn4ny88clcc    FK CONSTRAINT     �   ALTER TABLE ONLY public.offers
    ADD CONSTRAINT fk941mnejw7k5khxgn4ny88clcc FOREIGN KEY (price_id) REFERENCES public.prices(price_id);
 L   ALTER TABLE ONLY public.offers DROP CONSTRAINT fk941mnejw7k5khxgn4ny88clcc;
       public          postgres    false    2705    203    204            �
           2606    16450 "   offers fkc3roahbioubtd2qtqhadvur9v    FK CONSTRAINT     �   ALTER TABLE ONLY public.offers
    ADD CONSTRAINT fkc3roahbioubtd2qtqhadvur9v FOREIGN KEY (category_id) REFERENCES public.categories(category_id);
 L   ALTER TABLE ONLY public.offers DROP CONSTRAINT fkc3roahbioubtd2qtqhadvur9v;
       public          postgres    false    202    2697    203                  x������ � �            x������ � �            x������ � �     