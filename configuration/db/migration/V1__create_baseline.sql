--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.4
-- Dumped by pg_dump version 9.5.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: test; Type: SCHEMA; Schema: -; Owner: -
--

SET search_path = fridge;
SET default_tablespace = '';
SET default_with_oids = false;

--
-- Name: item; Type: TABLE; Schema: test; Owner: -
--

CREATE TABLE item (
  iid serial PRIMARY KEY,
  name character varying(20),
  create_at bigint,
  update_at bigint
);


--
-- Name: tag; Type: TABLE; Schema: test; Owner: -
--

CREATE TABLE tag (
  tid serial PRIMARY KEY,
  name character varying(20),
  create_at bigint,
  update_at bigint
);


--
-- Name: item_tag; Type: TABLE; Schema: test; Owner: -
--

CREATE TABLE item_tag (
  itid serial PRIMARY KEY,
  tid integer REFERENCES tag(tid),
  iid integer REFERENCES item(iid),
  create_at bigint,
  update_at bigint
);


--
-- Name: fridge_storage; Type: TABLE; Schema: test; Owner: -
--

CREATE TABLE fridge_storage (
  fsid serial PRIMARY KEY,
  quantity integer,
  iid integer REFERENCES item(iid),
  create_at bigint,
  update_at bigint
);


--
-- PostgreSQL database dump complete
--

