package com.bigcorp.booking.model;


    public class Serviette {

        private int id;
        private String name;


        public Serviette() {
        }

        public Serviette(String name, int id) {
            this.name = name;
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


    }


