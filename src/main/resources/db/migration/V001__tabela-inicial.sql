create table to_do (
	prioridade integer, 
	criacao datetime(6), 
	finalizacao datetime(6), 
	id bigint not null auto_increment, 
	descricao varchar(255), 
	realizada enum ('FINALIZADA','INICIADA'), 
	titulo varchar(255), 
	primary key (id)
) engine=InnoDB