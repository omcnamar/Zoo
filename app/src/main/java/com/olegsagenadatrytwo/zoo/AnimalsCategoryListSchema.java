package com.olegsagenadatrytwo.zoo;

/**
 * Created by omcna on 8/10/2017.
 */

public class AnimalsCategoryListSchema {

    public static final class Category{
        public static final String NAME = "Category";
        public static final class Columns {
            public static final String ID = "id";
            public static final String CATEGORY = "category";
        }
    }

    public static final class Animal{
        public static final String NAME = "Animal";
        public static final class Columns {
            public static final String ID = "id";
            public static final String CATEGORY = "category";
            public static final String ANIMALNAME = "animalname";
            public static final String EATS = "eats";
            public static final String IMAGE = "image";
        }
    }

    public static final String CATEGORY = "category";
    public static final String MAMMALS = "mammals";
    public static final String BIRDS = "birds";
    public static final String REPTILES = "reptiles";
    public static final String AMPHIBIANS = "amphibians";
    public static final String ARTHROPODS = "arthropods";

    public static final class Mammals{
        public static final String DOG = "dog";
        public static final String CAT = "cat";
    }
    public static final class Birds{
        public static final String EAGLE = "eagle";
        public static final String FALCON = "falcon";
    }
    public class Reptile{
        public static final String SNAKE = "snake";
        public static final String LIZARD = "lizard";
    }
    public class Amphibians{
        public static final String FROG = "frog";
        public static final String BULLFROG = "bullfrog";
    }
    public class Arthropods{
        public static final String MEROSTOMATA = "Merostomata";
        public static final String PYCNOGONIDA =  "Pycnogonida";
    }
}
