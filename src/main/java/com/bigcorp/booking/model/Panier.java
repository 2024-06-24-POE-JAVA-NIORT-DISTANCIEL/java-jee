package com.bigcorp.booking.model;

import java.util.ArrayList;
import java.util.List;

public class Panier {

        private List<Serviette> items;

        public Panier() {
            items = new ArrayList<>();
        }

        public void addItem(Serviette serviette) {
            items.add(serviette);
        }

        public List<Serviette> getItems() {
            return items;
        }
}
