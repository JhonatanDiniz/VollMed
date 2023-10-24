ALTER table pacientes add ativo boolean;

UPDATE pacientes SET ativo = true;

ALTER table pacientes ALTER column ativo SET NOT NULL;