CREATE table consulta(
    id bigserial not null unique,
    medico_id bigint not null,
    paciente_id bigint not null,
    data timestamp not null,

    primary key(id),
    constraint fk_consulta_medico_id foreign key(medico_id) references medicos(id),
    constraint fk_consulta_paciente_id foreign key(paciente_id) references pacientes(id)
)