package com.fit.maid.seminarskiv1.data;

import java.util.List;

public class PregledStanovaVM
    {
        public class Rows
        {
            public int id;
            public int kategorijaId;
            public String naziv;
            public String datumObjave;
            public double cijena;
        }

        public List<Rows> podaci;
    }