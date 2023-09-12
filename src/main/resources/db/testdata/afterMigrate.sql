set foreign_key_checks = 0;

lock tables to_do write;

truncate table to_do;

set foreign_key_checks = 1;

insert into to_do (prioridade , criacao , finalizacao , id , descricao , realizada, titulo ) values
	(1, '2023-07-08 10:00', '2023-07-12 10:00', 1, 'Lavar a casa todo do senhor Omar', 'FINALIZADA', 'Limpeza senhor Omar' );
	
insert into to_do (prioridade , criacao , finalizacao , id , descricao , realizada, titulo ) values
(1, '2023-07-10 10:00', '2023-07-13 10:00', 2, 'Agendar o exame de vista', 'FINALIZADA', 'Agendar exame' );
	
 unlock tables;