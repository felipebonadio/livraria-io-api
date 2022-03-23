INSERT IGNORE INTO `categoria` (`id`, `descricao`, `nome`)
VALUES ('1', 'Livros técnicos de arquitetura de software', 'Arquitetura de Software');

INSERT IGNORE INTO `categoria` (`id`, `descricao`, `nome`)
VALUES ('2', 'Livros técnicos de programação', 'Programação');

INSERT IGNORE INTO `categoria` (`id`, `descricao`, `nome`)
VALUES ('3', 'Livros técnicos de agilidade', 'Metodologias Ágeis');

INSERT IGNORE INTO `categoria` (`id`, `descricao`, `nome`)
VALUES ('4', 'Livros técnicos de arquitetura de hardware', 'Arquitetura de Hardware');

INSERT IGNORE INTO `autor` (`id`, `data_criacao`, `descricao`, `email`, `nome`)
VALUES ('1', '2022-03-07',
'Flávio Gomes da Silva Lisboa é bacharel em Ciência da Computação, especialista em tecnologia Java, certificado como engenheiro e arquiteto em PHP e Zend Framework e mestrando em Tecnologia e Sociedade. Tem experiência como programador e como gestor de projeto de software. É professor de disciplinas de programação orientada a objetos, testes unitários e frameworks de desenvolvimento. Tem seis livros publicados sobre programação na linguagem PHP e dez anos de experiência em treinamento para desenvolvedores de software. Foi keynote speaker na PHP Conference Brasil e palestrante e instrutor em diversos eventos de software. Trabalha como analista no Serviço Federal de Processamento de Dados e é contribuidor do projeto Nextcloud. É fã de histórias em quadrinhos, escreveu a hexalogia Rom, Biografia Não Autorizada e mantém o blog romocavaleirodoespaco.blogspot.com',
'flaviolisboa@email.com', 'Flávio Lisboa');

INSERT IGNORE INTO `autor` (`id`, `data_criacao`, `descricao`, `email`, `nome`)
VALUES ('2', '2022-03-07',
'Jorge Audy é escoteiro e agilista. Formou-se em 1986, foi concursado nos anos 80 e empresário nos 90. Também é Mestre em Administração na linha de pesquisa de Gestão da Informação e foi coordenador de desenvolvimento na ADP Brasil nos anos 2000 e no Grupo RBS até 2013, em equipes corporativas e de produtos digitais. Desde então, é consultor na DBServer. Praticante de métodos ágeis desde 2008, e Agile Coach desde 2011; e palestrante e participante ativo em eventos sobre métodos ágeis, equipes de alta performance e, mais especificamente, SCRUM. Confira mais sobre o autor em https://jorgekotickaudy.wordpress.com/about/.',
'jorgeaudy@email.com', 'Jorge Audy');

INSERT IGNORE INTO `autor` (`id`, `data_criacao`, `descricao`, `email`, `nome`)
VALUES ('3', '2022-03-07',
'Maurício é instrutor e desenvolvedor da Caelum, possui mais de 9 anos de experiência de desenvolvimento de softwares, tendo participado de projetos para grandes empresas nacionais e internacionais. Mauricio é também mestre em Ciência da Computação pela Universidade de São Paulo (USP), onde pesquisa sobre TDD e sua influência no design de sistemas orientados a objetos. Atualmente, é aluno de doutorado pelo mesmo instituto. Palestrante em eventos nacionais e internacionais, com destaque para sua apresentação no 1º Workshop Internacional sobre Test-Driven Development, localizado em Paris, no ano de 2010, Agile Brazil 2010, 2011 e 2012, e QCON SP 2010 e 2012. Mauricio gosta de discutir sobre TDD, melhores práticas e princípios de orientação a objetos, métodos ágeis de desenvolvimento de software e linguagens de programação. Está no Twitter como @mauricioaniche.',
'mauricioaniche@email.com', 'Mauricio Aniche');

INSERT IGNORE INTO `autor` (`id`, `data_criacao`, `descricao`, `email`, `nome`)
VALUES ('4', '2022-03-07',
'Everton Coimbra de Araújo atua na área de treinamento e desenvolvimento. É tecnólogo em processamento de dados pelo Centro de Ensino superior de Foz do Iguaçu, possui mestrado em Ciência da Computação pela UFSC e doutorado pela UNIOESTE em Engenharia Agrícola. É professor da Universidade Tecnológica Federal do Paraná (UTFPR), possui experiência na área de Ciência da Computação, com ênfase em Análise e Desenvolvimento de Sistemas, atuando principalmente nos seguintes temas: Desenvolvimento Web com Java e .NET, Persistência de Objetos e desenvolvimento de aplicativos para dispositivos móveis.',
'evertoncoimbra@email.com', 'Everton Coimbra de Araújo');

INSERT IGNORE INTO `livro` (`id`, `capa`, `data_publicacao`, `isbn`, `numero_paginas`, `preco`, `sumario`, `titulo`, `autor_id`, `categoria_id`)
VALUES ('1', 'https://cdn.shopify.com/s/files/1/0155/7645/products/p_large.jpg?v=1634930297',
 '2021-10-01', '978-65-86110-86-9', '138', '59,90',
 '<ul>1 Introdução: nossa meta é não bagunçar
    <li>1.1 O que é arquitetura?</li>
    <li>1.2 O que vem a seguir</li>
    <li>2 O projeto de sistema distribuído</li>
    <li>2.1 Aprendendo com os erros</li>
    <li>2.2 Nosso projeto de sistema distribuído</li>
    <li>2.3 Sistema distribuído</li>
    <li>3 O microsserviço de fila</li>
    <li>3.1 A estrutura de filas no nosso sistema de auditoria</li>
    <li>3.2 Instalando o Apache ActiveMQ</li>
    <li>3.3 Simulação de produtor e consumidor</li>
   </ul>

   <ul>4 O microsserviço produtor
      <li>4.1 A abstração no desenvolvimento de software</li>
      <li>4.2 A escolha pela linguagem Go</li>
      <li>4.3 O podips-reader</li>
      <li>5 O microsserviço consumidor</li>
      <li>5.1 A escolha pela linguagem Python</li>
      <li>5.2 O podips-writer</li>
    </ul>

    <ul>6 O microsserviço de monitoramento</li>
      <li>6.1 O podips-monitor</li>
      <li>6.2 Criação do podips-monitor</li>
      <li>6.3 Implementação do podips-monitor</li>
      <li>6.4 Documentação da API</li>
   </ul>

    <ul>7 O microsserviço agendado
      <li>7.1 O podips-cronjob</li>
      <li>7.2 Implementação do podips-cronjob</li>
   </ul>

    <ul>8 Conclusão</ul>

   <ul>9 Referências</ul>
   ',
 'Arquitetura de software distribuído: Boas práticas para um mundo de microsserviços', '1', '1');

INSERT IGNORE INTO `livro` (`id`, `capa`, `data_publicacao`, `isbn`, `numero_paginas`, `preco`, `sumario`, `titulo`, `autor_id`, `categoria_id`)
VALUES ('2', 'https://cdn.shopify.com/s/files/1/0155/7645/products/cover_f4484d23-c1e7-4654-994f-9f4ac882fba0_large.jpg?v=1631669980',
 '2020-10-01', '978-65-86110-49-4', '273', '69,90',
 '<ul>1 Introdução
    <li>1.1 Manutenção de software</li>
    <li>1.2 Arquitetura de software</li>
    <li>1.3 Conclusão</li>
   </ul>

   <ul>2 Boas práticas de desenvolvimento
      <li>2.1 Princípios da boa programação</li>
      <li>2.2 Calistenia de objetos</li>
      <li>2.3 Recomendações para desenvolver em PHP</li>
   </ul>

   <ul>3 Bússola do ambiente de desenvolvimento
      <li>3.1 Apache, MySQL e PHP</li>
      <li>3.2 Ambiente integrado de desenvolvimento</li>
    </ul>

    <ul>4 Bússola da estrutura de PHP
      <li>4.1 Configuração do PHP</li>
      <li>4.2 Tipos de dados</li>
      <li>4.3 Strings</li>
      <li>4.4 Arrays</li>
    </ul>

    <ul>5 Bússola de funções e classes de PHP
      <li>5.1 Funções</li>
      <li>5.2 Classes e objetos</li>
      <li>5.3 Data e hora</li>
      <li>5.4 Diretórios e arquivos</li>
      <li>5.5 Monitoração e medição em segundo plano</li>
      <li>5.6 Servidor embutido</li>
      <li>5.7 Modo interativo</li>
      <li>5.8 Standard PHP Library (SPL)</li>
    </ul>

    <ul>6 MVC e MVVM com Laminas
      <li>6.1 Criação do projeto</li>
      <li>6.2 Edição de projeto</li>
      <li>6.3 Criação de módulo</li>
      <li>6.4 Criação das rotas</li>
      <li>6.5 Criação das ações do controlador</li>
      <li>6.6 Criação de layout</li>
      <li>6.7 Integração do Eclipse e do Composer</li>
    </ul>

    <ul>7 Mapeamento objeto-relacional
      <li>7.1 Banco de dados, modelo e mapeador</li>
      <li>7.2 Conexão e mapeamento objeto-relacional como serviço</li>
      <li>7.3 Implementando as ações do controlador</li>
    </ul>

    <ul>8 Formulários dinâmicos
      <li>8.1 Criando formulários dinâmicos</li>
      <li>8.2 Validando formulários dinâmicos</li>
      <li>8.3 Ajustes na alteração de setores</li>
      <li>8.4 Ajustes na remoção de setores</li>
      <li>8.5 Modelo e mapeador com relacionamento</li>
      <li>8.6 Mapeando várias tabelas</li>
      <li>8.7 Formulário com mapeador</li>
    </ul>

    <ul>9 Visão e controle com relacionamentos
      <li>9.1 Visões de um modelo relacionado</li>
      <li>9.2 Um controlador com dois modelos pelo preço de um</li>
      <li>9.3 Ajustando o módulo Application</li>
    </ul>

    <ul>10 Guia de referência rápida do MVC do Laminas
      <li>10.1 Modelos</li>
      <li>10.2 Controladores</li>
      <li>10.3 Visões</li>
      <li>10.4 Tipo de documento</li>
      <li>10.5 Mapeadores</li>
      <li>10.6 Formulários</li>
      <li>10.7 Aplicação</li>
      <li>10.8 Fluxo de processamento da requisição</li>
    </ul>

    <ul>11 Considerações finais</ul>
    <ul>12 Referencial teórico</ul>
    <ul>13 Referências</ul>',
 'Do PHP ao Laminas: Domine as boas práticas', '1', '2');


INSERT IGNORE INTO `livro` (`id`, `capa`, `data_publicacao`, `isbn`, `numero_paginas`, `preco`, `sumario`, `titulo`, `autor_id`, `categoria_id`)
VALUES ('3', 'https://cdn.shopify.com/s/files/1/0155/7645/products/ProgramacaoWebavancadacomPHP_ebook_large.jpg?v=1631651615',
 '2020-07-01', '978-65-86110-25-8', '205', '69,90',
 '<ul>1 Introdução
      <li>1.1 PHP e MySQL</li>
      <li>1.2 Ambiente integrado de desenvolvimento</li>
    </ul>

    <ul>2 PHP para quem tem pressa
      <li>2.1 Um cadastro usando o sistema de arquivos</li>
      <li>2.2 Um cadastro usando banco de dados relacional</li>
      <li>2.3 Um cadastro com função definida pelo programador</li>
      <li>2.4 Um cadastro com uma classe abstrata e duas classes concretas</li>
      <li>2.5 Um cadastro com uma classe controladora de requisições</li>
    </ul>

    <ul>3 A aplicação de exemplo
      <li>3.1 Instalação da aplicação</li>
      <li>3.2 De que se trata a aplicação</li>
      <li>3.3 O que falta na aplicação</li>
    </ul>

    <ul>4 Desenvolvimento orientado a componentes
      <li>4.1 Usar é melhor que criar, mas nem sempre</li>
      <li>4.2 Gerenciando componentes</li>
    </ul>

    <ul>5 Desenvolvimento orientado a eventos</ul>

   <ul>6 Injeção de dependências
      <li>6.1 Injeção de dependência no controlador</li>
      <li>6.2 Injeção de dependência no mapeador de tabelas</li>
    </ul>

   <ul>7 Segurança de aplicações web
      <li>7.1 Tratamento e neutralização de saída perigosa</li>
      <li>7.2 Ataques XSS</li>
      <li>7.3 Ataques de injeção de SQL</li>
      <li>7.4 Ataques de simulação de requisição</li>
      <li>7.5 Melhores práticas de segurança</li>
    </ul>

    <ul>8 Filtros e conversores de dados
      <li>8.1 Laminas, Filter</li>
      <li>8.2 Filtros predefinidos</li>
      <li>8.3 Cadeias de filtro</li>
      <li>8.4 Criando filtros customizados</li>
      <li>8.5 Laminas, InputFilter, InputFilter</li>
    </ul>

    <ul>9 Validadores de dados
      <li>9.1 Laminas, Validator</li>
      <li>9.2 Customizando mensagens</li>
      <li>9.3 Validadores predefinidos</li>
      <li>9.4 Cadeias de validação</li>
      <li>9.5 Criando validadores customizados</li>
   </ul>

    <ul>10 Criptografia
         <li>10.1 Criptografando textos</li>
         <li>10.2 Criptografando e verificando senhas</li>
   </ul>

   <ul>11 Autenticação
      <li>11.1 Laminas, Authentication, AuthenticationService</li>
      <li>11.2 Persistência de identidade</li>
      <li>11.3 Resultados de autenticação</li>
      <li>11.4 Retornos possíveis para uma tentativa de autenticação</li>
      <li>11.5 Criação de adaptadores customizados de autenticação</li>
      <li>11.6 Remoção da identidade armazenada</li>
      <li>11.7 Implementando autenticação na aplicação</li>
    </ul>

    <ul>12 Controle de permissões
      <li>12.1 Laminas, Permissions, Acl</li>
      <li>12.2 Laminas, Permissions, Rbac</li>
    </ul>

    <ul>13 Mapeamento objeto-relacional com Laminas, Db
      <li>13.1 Laminas, Db</li>
      <li>13.2 Criando um projeto com o ORM do Zend, Db</li>
   </ul>

    <ul>14 Web services e APIs
      <li>14.1 XML-RPC</li>
      <li>14.2 SOAP</li>
      <li>14.3 JSON-RPC</li>
    </ul>

    <ul>15 Serviços internos de uma aplicação web
      <li>15.1 Laminas, Config</li>
      <li>15.2 Laminas, Log</li>
    </ul>
    
    <ul>16 Internacionalização
      <li>16.1 Laminas, I18n</li>
    </ul>
    
    <ul>17 Referências</ul>',
'Programação Web avançada com PHP: Construindo software com componentes', '1', '2');

INSERT IGNORE INTO `livro` (`id`, `capa`, `data_publicacao`, `isbn`, `numero_paginas`, `preco`, `sumario`, `titulo`, `autor_id`, `categoria_id`)
VALUES ('4', 'https://cdn.shopify.com/s/files/1/0155/7645/products/Scrum360_ebook_large.jpg?v=1631650114',
 '2015-05-01', '978-85-5519-022-3', '189', '59,90',
 '<ul>1 Modelo mental
      <li>1.1 Escotismo</li>
      <li>1.2 Muros e feudos</li>
      <li>1.3 Desarmando rótulos e paixões</li>
      <li>1.4 Sociedade industrial ou do conhecimento</li>
      <li>1.5 Autoconhecimento</li>
      <li>1.6 Não mudo nada porque não posso mudar tudo</li>
      <li>1.7 Felicidade</li>
      <li>1.8 Individualismo ou coletivo</li>
      <li>1.9 Confiança e melhoria são como uma poupança</li>
      <li>1.10 Mudança de hábito</li>
      <li>1.11 Morte às baias e aos gaveteiros</li>
      <li>1.12 Equilíbrio</li>
    </ul>

    <ul>2 Sobre os ombros de gigantes
      <li>2.1 Lei Yerkes-Dodson (1805)</li>
      <li>2.2 Ciclo de Deming (1950)</li>
      <li>2.3 Pareto e Juran (1951)</li>
      <li>2.4 Teoria da dissonância cognitiva (1957)</li>
      <li>2.5 Teoria da contingência (1958)</li>
      <li>2.6 Curva de Tuckman (1965)</li>
      <li>2.7 Teoria da agência (1972)</li>
      <li>2.8 Teoria institucional (1977)</li>
      <li>2.9 Teoria das restrições (1984)</li>
      <li>2.10 Matriz Cynefin (1999)</li>
      <li>2.11 Gamification (2002)</li>
   </ul>

    <ul>3 Princípios ágeis
      <li>3.1 Por que o método se chama "Ágil"?</li>
      <li>3.2 Granuralidade ágil</li>
      <li>3.3 Manifesto ágil</li>
      <li>3.4 Princípios ágeis</li>
      <li>3.5 Algumas pessoas olham para o lado errado</li>
      <li>3.6 Gestão do tempo</li>
      <li>3.7 Estratégias de adoção</li>
      <li>3.8 Pacto de equipe</li>
   </ul>


    <ul>4 Introdução ao método
      <li>4.1 Scrum</li>
      <li>4.2 Os três pilares do Scrum</li>
      <li>4.3 Overview do método Scrum</li>
      <li>4.4 Product Owner</li>
      <li>4.5 Scrum master</li>
      <li>4.6 Equipe de desenvolvimento</li>
      <li>4.7 Fases do Scrum: Discovery x Delivery</li>
    </ul>

    <ul>5 Discovery
      <li>5.1 Visão</li>
      <li>5.2 User Story</li>
      <li>5.3 Critérios de aceitação</li>
      <li>5.4 Reuniões de elicitação</li>
      <li>5.5 Mínimo Produto Viável (MVP)</li>
      <li>5.6 User Story Mapping</li>
      <li>5.7 Product Backlog e Sprint Backlog</li>
   </ul>

    <ul>6 Delivery
      <li>6.1 Sprint Planning</li>
      <li>6.2 Planning poker</li>
      <li>6.3 TDD – Test Driven Development</li>
      <li>6.4 Scrum board (quadro de tarefas)</li>
      <li>6.5 Tarefas</li>
      <li>6.6 Daily</li>
      <li>6.7 Burndown</li>
      <li>6.8 Artefatos adicionais</li>
   </ul>

    <ul>7 Melhoria contínua
      <li>7.1 Review</li>
      <li>7.2 Entrega de pacotes</li>
      <li>7.3 Retrospectiva</li>
      <li>7.4 Melhoria contínua em TI é com Dojos</li>
      <li>7.5 Resumo de 4 dicas em cada momento</li>
   </ul>

    <ul>8 Gestão e liderança
      <li>8.1 Esferas de atuação</li>
      <li>8.2 Gestão e liderança ágil</li>
      <li>8.3 Agile é uma revolução permanente</li>
      <li>8.4 Evite ser ágil só enquanto tudo dá certo</li>
      <li>8.5 Ensaio sobre estimativas</li>
      <li>8.6 Sem confiança, não existe agilidade</li>
      <li>8.7 Contratos ágeis</li>
    </ul>

    <ul>9 Outros métodos
      <li>9.1 XP – eXtreme Programming</li>
      <li>9.2 Lean Software Development</li>
      <li>9.3 Kanban</li>
      <li>9.4 BDD – Behavior Driven Development</li>
      <li>9.5 DDD – Domain Driven Design</li>
      <li>9.6 PMBOK</li>
      <li>9.7 Engenharia de software</li>
   </ul>

    <ul>10 Ecossistema
      <li>10.1 Execução!</li>
      <li>10.2 PDCL ágil</li>
      <li>10.3 Estratégia para inovação</li>
      <li>10.4 Manifesto ágil ajustado para outras áreas</li>
      <li>10.5 Um PDCL no financeiro</li>
      <li>10.6 Rainforest</li>
   </ul>

    <ul>11 Gestão do conhecimento
         <li>11.1 Gestão do conhecimento</li>
         <li>11.2 Conceito de Ba</li>
         <li>11.3 Comunidades de prática</li>
         <li>11.4 Agile subway maps e dashboards</li>
         <li>11.5 Tipos de eventos</li>
         <li>11.6 Hackatona PDCL</li>
         <li>11.7 Colaboração é a menor distância</li>
         <li>11.8 Eventos, confrarias e voluntariado</li>
         <li>11.9 Acho que aprendi algo novo, e agora?</li>
      </ul>',
'Scrum 360: Um guia completo e prático de agilidade', '2', '3');

INSERT IGNORE INTO `livro` (`id`, `capa`, `data_publicacao`, `isbn`, `numero_paginas`, `preco`, `sumario`, `titulo`, `autor_id`, `categoria_id`)
VALUES ('5', 'https://cdn.shopify.com/s/files/1/0155/7645/products/OrientacaoaObjetoseSOLIDparaNinjas_ebook_large.jpg?v=1631653546',
 '2015-03-01', '978-85-5519-037-7', '155', '59,90',
 '
 <ul>1 - Orientação a Objetos, pra que te quero?
   <li>1.1 - Qual o público deste livro?</li>
  </ul>

   <ul>2 - A coesão e o tal do SRP
      <li>2.1 - Um exemplo de classe não coesa</li>
      <li>2.2 - Qual o problema dela?</li>
      <li>2.3 - Em busca da coesão</li>
      <li>2.4 - Uma pitada de encapsulamento</li>
      <li>2.5 - Quando usar métodos privados?</li>
      <li>2.6 - Falta de coesão em controllers</li>
      <li>2.7 - Inveja da outra classe</li>
      <li>2.8 - SRP ─ Single Responsibility Principle</li>
      <li>2.9 - Separação do modelo, infraestrutura, e a tal da arquitetura hexagonal</li>
      <li>2.10 - Conclusão</li>
   </ul>

   <ul>3 - Acoplamento e o tal do DIP
      <li>3.1 - Qual o problema dela?</li>
      <li>3.2 - Estabilidade de classes</li>
      <li>3.3 - Buscando por classes estáveis</li>
      <li>3.4 - DIP ─ Dependency Inversion Principle</li>
      <li>3.5 - Um outro exemplo de acoplamento</li>
      <li>3.6 - Dependências lógicas</li>
      <li>3.7 - Conclusão</li>
   </ul>

   <ul>4 - Classes abertas e o tal do OCP
      <li>4.1 - Qual o problema dela?</li>
      <li>4.2 - OCP ─ Princípio do Aberto-Fechado</li>
      <li>4.3 - Classes extensíveis</li>
      <li>4.4 - A testabilidade agradece!</li>
      <li>4.5 - Um exemplo real</li>
      <li>4.6 - Ensinando abstrações desde a base</li>
      <li>4.7 - Conclusão</li>
   </ul>

   <ul>5 - O encapsulamento e a propagação de mudanças
      <li>5.1 - Qual o problema dela?</li>
      <li>5.2 - Intimidade inapropriada</li>
      <li>5.3 - Um sistema OO é um quebra-cabeças</li>
      <li>5.4 - Tell, Dont Ask</li>
      <li>5.5 - Procurando por encapsulamentos problemáticos</li>
      <li>5.6 - A famosa Lei de Demeter</li>
      <li>5.7 - Getters e setters pra tudo, não!</li>
      <li>5.8 - Corrigindo o código inicial</li>
      <li>5.9 - Modelos anêmicos</li>
      <li>5.10 - Conclusão</li>
   </ul>

   <ul>6 - Herança x composição e o tal do LSP
      <li>6.1 - Qual o problema dela?</li>
      <li>6.2 - LSP ─ Liskov Substitutive Principle</li>
      <li>6.3 - O exemplo do quadrado e retângulo</li>
      <li>6.4 - Acoplamento entre a classe pai e a classe filho</li>
      <li>6.5 - Favoreça a composição</li>
      <li>6.6 - Herança para DSLs e afins</li>
      <li>6.7 - Quando usar herança então?</li>
      <li>6.8 - Pacotes: como usá-los?</li>
      <li>6.9 - Conclusão</li>
   </ul>

   <ul>7 - Interfaces magras e o tal do ISP
      <li>7.1 - Interfaces coesas e magras</li>
      <li>7.2 - Pensando na interface mais magra possível</li>
      <li>7.3 - E os tais dos repositórios do DDD?</li>
      <li>7.4 - Fábricas ou injeção de dependência?</li>
      <li>7.5 - Conclusão</li>
   </ul>

   <ul>8 - Consistência, objetinhos e objetões
      <li>8.1 - Construtores ricos</li>
      <li>8.2 - Validando dados</li>
      <li>8.3 - Teorema do bom vizinho e nulos para lá e para cá</li>
      <li>8.4 - Tiny Types é uma boa ideia?</li>
      <li>8.5 - DTOs do bem</li>
      <li>8.6 - Imutabilidade x mutabilidade</li>
      <li>8.7 - Classes que são feias por natureza</li>
      <li>8.8 - Nomenclatura de métodos e variáveis</li>
      <li>8.9 - Conclusão</li>
   </ul>

   <ul>9 - Maus cheiros de design
      <li>9.1 - Refused Bequest</li>
      <li>9.2 - Feature Envy</li>
      <li>9.3 - Intimidade inapropriada</li>
      <li>9.4 - God Class</li>
      <li>9.5 - Divergent Changes</li>
      <li>9.6 - Shotgun Surgery</li>
      <li>9.7 - Entre outros</li>
   </ul>

   <ul>10 - Métricas de código
      <li>10.1 - Complexidade ciclomática</li>
      <li>10.2 - Tamanho de métodos</li>
      <li>10.3 - Coesão e a LCOM</li>
      <li>10.4 - Acoplamento aferente e eferente</li>
      <li>10.5 - Má nomenclatura</li>
      <li>10.6 - Como avaliar os números encontrados?</li>
      <li>10.7 - Ferramentas</li>
   </ul>

   <ul>11 - Conclusão
      <li>11.1 - Onde posso ler mais sobre isso?</li>
      <li>11.2 - Obrigado!</li>
   </ul>',
 'Orientação a Objetos e SOLID para Ninjas: Projetando classes flexíveis', '3', '2');

 INSERT IGNORE INTO `livro` (`id`, `capa`, `data_publicacao`, `isbn`, `numero_paginas`, `preco`, `sumario`, `titulo`, `autor_id`, `categoria_id`)
 VALUES ('6', 'https://cdn.shopify.com/s/files/1/0155/7645/products/IntroducaoaprogramacaoemC_ebook_large.jpg?v=1631718790',
 '2015-08-01', '978-85-5519-088-9', '290', '79,90',
 '<ul>1 Introdução
      <li>1.1 A quem se destina este livro?</li>
      <li>1.2 Como devo lê-lo?</li>
   </ul>

   <ul>2 Jogo de adivinhação
      <li>2.1 Como ele ficará?</li>
      <li>2.2 É hora de começar!</li>
   </ul>
   
   <ul>3 Variáveis
      <li>3.1 Nosso primeiro programa</li>
      <li>3.2 Declarando variáveis</li>
      <li>3.3 Lendo do teclado</li>
      <li>3.4 Entendendo o compilador</li>
      <li>3.5 Resumindo</li>
   </ul>
   
   <ul>4 Controle de fluxo com ifs e fors
      <li>4.1 Escopo de variáveis</li>
      <li>4.2 Loops e for</li>
      <li>4.3 Parando loops</li>
      <li>4.4 Defines, constantes e números mágicos</li>
      <li>4.5 O else if</li>
      <li>4.6 Break e continue</li>
      <li>4.7 O loop while</li>
      <li>4.8 Loops infinitos</li>
      <li>4.9 Resumindo</li>
   </ul>
   
   <ul>5 Tipos de dados e operações matemáticas
      <li>5.1 Operações matemáticas</li>
      <li>5.2 Outros tipos numéricos</li>
      <li>5.3 Conversões e casting</li>
      <li>5.4 Funções matemáticas</li>
      <li>5.5 Números randômicos</li>
      <li>5.6 Resumindo</li>
   </ul>
   
   <ul>6 Finalizando o jogo
      <li>6.1 Switch e case</li>
      <li>6.2 Novamente usando variáveis e escopos</li>
      <li>6.3 Embelezando o jogo</li>
      <li>6.4 Acabamos o primeiro jogo!</li>
      <li>6.5 Resumindo</li>
   </ul>
   
   <ul>7 Exercícios
      <li>7.1 Melhorando o jogo de adivinhação</li>
      <li>7.2 Outros desafios</li>
   </ul>
   
   <ul>8 Jogo de forca
      <li>8.1 Como ele ficará?</li>
      <li>8.2 É hora de começar!</li>
   </ul>
   
   <ul>9 Arrays
      <li>9.1 Strings e array de chars</li>
      <li>9.2 Varrendo o array</li>
      <li>9.3 Laços encadeados</li>
      <li>9.4 Resumindo</li>
   </ul>
   
   <ul>10 Números binários
      <li>10.1 Binário e letras</li>
      <li>10.2 Bits: 8, 16, 32, 64</li>
      <li>10.3 Bits e números com ponto flutuante</li>
      <li>10.4 Hexadecimal</li>
      <li>10.5 Bits e imagens</li>
      <li>10.6 Resumindo</li>
   </ul>
   
   <ul>11 Funções e ponteiros
      <li>11.1 Escrevendo funções</li>
      <li>11.2 Mais funções</li>
      <li>11.3 Passando parâmetros para funções</li>
      <li>11.4 Ponteiros</li>
      <li>11.5 Passagem por referência</li>
      <li>11.6 Arrays e ponteiros</li>
      <li>11.7 Funções com retorno</li>
      <li>11.8 Extraindo mais funções</li>
      <li>11.9 Variáveis globais</li>
      <li>11.10 Resumindo</li>
   </ul>
   
   <ul>12 Entrada e saída (I/O)
      <li>12.1 Header files</li>
      <li>12.2 Lendo arquivos</li>
      <li>12.3 Escrevendo no arquivo</li>
      <li>12.4 Mais sobre I/O</li>
      <li>12.5 Resumindo</li>
   </ul>
   
   <ul>13 Finalizando o jogo
      <li>13.1 Evitando repetição de código</li>
      <li>13.2 Extraindo funções</li>
      <li>13.3 Ifs ternários</li>
      <li>13.4 Últimos detalhes</li>
      <li>13.5 Resumindo</li>
   </ul>
   
   <ul>14 Exercícios
      <li>14.1 Jogo de adivinhação</li>
      <li>14.2 Jogo de forca</li>
      <li>14.3 Outros desafios</li>
   </ul>
   
   <ul>15 Jogo Foge-foge
      <li>15.1 Como nosso jogo vai ficar?</li>
      <li>15.2 É hora de começar!</li>
   </ul>
   
   <ul>16 Matrizes
      <li>16.1 Ponteiros de ponteiros</li>
      <li>16.2 Alocação dinâmica de memória</li>
      <li>16.3 Resumindo</li>
   </ul>
   
   <ul>17 Structs
      <li>17.1 Definindo uma struct</li>
      <li>17.2 Ponteiros para structs</li>
      <li>17.3 Introdução à análise de algoritmos</li>
      <li>17.4 Resumindo</li>
   </ul>
   
   <ul>18 Programando como um profissional
      <li>18.1 Novamente, responsabilidades</li>
      <li>18.2 Novamente, constantes</li>
      <li>18.3 Usando estruturas auxiliares</li>
      <li>18.4 Um pouco de inteligência artificial</li>
      <li>18.5 Acoplamento, encapsulamento e assinaturas de funções</li>
      <li>18.6 Resumindo</li>
   </ul>
   
   <ul>19 Recursividade
      <li>19.1 Entendendo recursão</li>
      <li>19.2 Complicando o algoritmo recursivo</li>
      <li>19.3 Resumindo</li>
   </ul>
   
   <ul>20 Outras diretivas de compilação
      <li>20.1 Ifdefs e Ifndefs</li>
      <li>20.2 Resumindo</li>
   </ul>
   
   <ul>21 Exercícios
      <li>21.1 Jogo Foge-foge</li>
      <li>21.2 Outros desafios</li>
   </ul>
   
   <ul>22 O que fazer agora?</ul>
   <ul>23 Apêndice A: instalando o compilador</ul>
   
   <ul>24 Apêndice B: códigos
      <li>24.1 Jogo da adivinhação</li>
      <li>24.2 Jogo de forca</li>
      <li>24.3 Foge-foge</li>
   </ul>',
 'Introdução à programação em C: Os primeiros passos de um desenvolvedor', '3', '2');

  INSERT IGNORE INTO `livro` (`id`, `capa`, `data_publicacao`, `isbn`, `numero_paginas`, `preco`, `sumario`, `titulo`, `autor_id`, `categoria_id`)
  VALUES ('7', 'https://cdn.shopify.com/s/files/1/0155/7645/products/OrientacaoaObjetosemC__ebook_large.jpg?v=1631653145',
 '2017-11-01', '978-65-86110-00-5', '236', '69,90',
 '<ul>1 Introdução à Orientação a Objetos
      <li>1.1 Alguns componentes da Orientação a Objetos</li>
      <li>1.2 Características da Orientação a Objetos</li>
      <li>1.3 A escolha da plataforma e da linguagem</li>
      <li>1.4 Conclusão sobre as atividades realizadas no capítulo</li>
   </ul>
   
   <ul>2 Iniciando a implementação em Orientação a Objetos
      <li>2.1 A primeira classe</li>
      <li>2.2 Tipos de dados</li>
      <li>2.3 Uma aplicação de teste e início com o Visual Studio</li>
      <li>2.4 Execução da classe de teste</li>
      <li>2.5 Outros exemplos de classes</li>
      <li>2.6 Conclusão sobre as atividades realizadas no capítulo</li>
   </ul>
   
   <ul>3 Associações e inicialização de objetos
      <li>3.1 Identificação e implementação de associações</li>
      <li>3.2 Teste das associações</li>
      <li>3.3 A inicialização de objetos</li>
      <li>3.4 Composição como associação</li>
      <li>3.5 Outros exemplos de associações</li>
      <li>3.6 Conclusão sobre as atividades realizadas no capítulo</li>
   </ul>
   
   <ul>4 Coleções, agregação, identidade e recuperação de objetos
      <li>4.1 O uso de coleções</li>
      <li>4.2 A identidade de um objeto</li>
      <li>4.3 Agregação como associação</li>
      <li>4.4 Recuperação de objetos de uma coleção por meio do LINQ</li>
      <li>4.5 Outros exemplos com coleções</li>
      <li>4.6 Conclusão sobre as atividades realizadas no capítulo</li>
   </ul>
   
   <ul>5 Herança, polimorfismo e exceção
      <li>5.1 Herança por extensão</li>
      <li>5.2 Herança por implementação, com interfaces e polimorfismo</li>
      <li>5.3 Exceções</li>
      <li>5.4 Sobrecarga e sobreposição de métodos</li>
      <li>5.5 Outro exemplo com herança por extensão</li>
      <li>5.6 Outro exemplo com herança por implementação</li>
      <li>5.7 Comentários adicionais para polimorfismo, sobrecarga, sobreposição e exceções</li>
      <li>5.8 Mas então? Herança ou composição?</li>
      <li>5.9 Conclusão sobre as atividades realizadas no capítulo</li>
   </ul>
   
   <ul>6 Orientação a Objetos e Padrões de Projeto
      <li>6.1 O padrão comportamental Strategy</li>
      <li>6.2 O padrão comportamental Chain of Responsibility</li>
      <li>6.3 Conclusão sobre as atividades realizadas no capítulo</li>
   </ul>
   
   <ul>7 Solução dividida em camadas
      <li>7.1 Contextualização sobre as camadas</li>
      <li>7.2 Os projetos que representarão as camadas</li>
      <li>7.3 Implementação da interação com o usuário</li>
      <li>7.4 Modificadores de acesso/escopo e encapsulamento</li>
      <li>7.5 Conclusão sobre as atividades realizadas no capítulo</li>
   </ul>
   
   <ul>8 Acesso a banco de dados
      <li>8.1 Introdução ao ADO.NET e criação da base utilizando o Visual Studio</li>
      <li>8.2 Realizando operações relacionadas ao CRUD em uma tabela de dados</li>
      <li>8.3 Conclusão sobre as atividades realizadas no capítulo</li>
   </ul>
   
   <ul>9 O Entity Framework Core como ferramenta para mapeamento objeto-relacional na persistência
      <li>9.1 Criação do projeto para a aplicação do EF Core</li>
      <li>9.2 Recuperando dados com o EF Core</li>
      <li>9.3 Gravação na base de dados</li>
      <li>9.4 Associações com o EF Core</li>
      <li>9.5 Dados de teste para nossa base de dados</li>
      <li>9.6 A interface com o usuário para a associação</li>
      <li>9.7 Fechando as funcionalidades para um CRUD com associação</li>
      <li>9.8 Conclusão sobre as atividades realizadas no capítulo</li>
   </ul>
   
   <ul>10 Conclusão e caminhos futuros</ul>',
 'Orientação a Objetos em C#: Conceitos e implementações em .NET', '4', '2');

   INSERT IGNORE INTO `livro` (`id`, `capa`, `data_publicacao`, `isbn`, `numero_paginas`, `preco`, `sumario`, `titulo`, `autor_id`, `categoria_id`)
   VALUES ('8', 'https://cdn.shopify.com/s/files/1/0155/7645/products/Aprofundandoemflutter_Amazon_large.jpg?v=1631564172',
 '2021-06-01', '978-65-86110-75-3', '319', '79,90',
'<ul>1 Introdução
   <li>1.1 O que são os widgets?</li>
   <li>1.2 O que veremos neste livro?</li>
   <li>1.3 Preparação de nosso ambiente de trabalho</li>
</ul>

   <ul>2 Ambientando-se com o Flutter
      <li>2.1 Instalação e configuração do Flutter e do Dart no Android Studio</li>
      <li>2.2 O App criado pelo template do Android Studio</li>
      <li>2.3 Vamos aprofundar no código de exemplo</li>
      <li>2.4 Enfim, o widget do template</li>
      <li>2.5 Sobre os widgets do exemplo do template</li>
      <li>2.6 Hot Reload e Flutter Hot Restart</li>
   </ul>

   <ul>3 A Splash Screen na inicialização da aplicação
      <li>3.1 Inicialização da aplicação</li>
      <li>3.2 Customização de um widget para uma imagem circular</li>
      <li>3.3 O widget para a Splash Screen</li>
      <li>3.4 Adicionando assets ao nosso aplicativo</li>
      <li>3.5 A Splash Screen em execução</li>
      <li>3.6 Recursos vistos no capítulo</li>
   </ul>

   <ul>4 Persistência de dados com Shared Preferences
      <li>4.1 Tela de boas-vindas com Shared Preferences</li>
      <li>4.2 A execução para exibir as boas-vindas</li>
      <li>4.3 Interação com o CheckBox e RaisedButton</li>
      <li>4.4 Registro e leitura da Shared Preference</li>
   </ul>

   <ul>5 Um menu com opções de acesso e início com animações
      <li>5.1 Criação de nosso Drawer</li>
      <li>5.2 O cabeçalho de nosso Drawer</li>
      <li>5.3 As opções oferecidas ao usuário pelo Drawer</li>
      <li>5.4 Refatoração para os ListTiles</li>
      <li>5.5 Customização para os ListTiles internos</li>
   </ul>

   <ul>6 Abrindo o Drawer via código e uma animação com BLoC
      <li>6.1 Contextualização sobre o problema e a solução proposta</li>
      <li>6.2 Personalização do Drawer</li>
      <li>6.3 Abertura, fechamento e estado no Drawer</li>
      <li>6.4 Animação do logo do app</li>
      <li>6.5 Atualização de estado com BLoC</li>
   </ul>

   <ul>7 Rotas, transições entre elas e o formulário para palavras
      <li>7.1 A classe modelo para o formulário</li>
      <li>7.2 Implementações para uso de BLoC no formulário</li>
      <li>7.3 Mixin no Dart</li>
      <li>7.4 A rota para o formulário de registro de palavras</li>
      <li>7.5 O controle de rotas</li>
      <li>7.6 O formulário para registro das palavras</li>
      <li>7.7 Confirmação dos dados informados</li>
   </ul>

   <ul>8 Persistência na inserção e BLoC na recuperação de dados e animação na transição de rotas
      <li>8.1 O SQLite como base de dados local</li>
      <li>8.2 A manipulação de dados no SQLite</li>
      <li>8.3 Utilização do DAO em nossa visão de inserção</li>
      <li>8.4 A visualização de todas as palavras já registradas no SQLite</li>
      <li>8.5 BLoC para a recuperação dos dados</li>
      <li>8.6 A rota para a visualização de palavras registradas</li>
      <li>8.7 A rolagem infinita dos dados</li>
   </ul>

   <ul>9 Remoção de dados e atualização do ListView com destaque para alterações realizadas
      <li>9.1 A remoção da palavra do ListView</li>
      <li>9.2 A remoção da palavra da tabela de dados</li>
      <li>9.3 A remoção da palavra da coleção que popula o ListView</li>
      <li>9.4 A alteração de uma palavra já registrada</li>
      <li>9.5 Atualização da listagem com palavras inseridas e alteradas pela rota de CRUD</li>
      <li>9.6 Destaque no ListView para a palavra alterada</li>
      <li>9.7 Finalizações em nosso CRUD</li>
   </ul>

   <ul>10 Funcionamento e interação do usuário com o jogo e o teclado para as letras
      <li>10.1 Contextualização do jogo</li>
      <li>10.2 O esboço de layout para nosso jogo</li>
      <li>10.3 Layout real do jogo</li>
      <li>10.4 A implementação para o funcionamento do jogo</li>
      <li>10.5 A interação do usuário com o jogo</li>
      <li>10.6 O teclado do jogo com as letras</li>
   </ul>

   <ul>11 Validação da letra escolhida e verificação de vitória e derrota
      <li>11.1 Verificação da existência da letra</li>
      <li>11.2 Errou, começa a animação da forca</li>
      <li>11.3 Fechamentos para concluir o jogo</li>
      <li>11.4 O jogador ganhou</li>
      <li>11.5 Reinício após a vitória</li>
      <li>11.6 O jogador perdeu</li>
      <li>11.7 Ajuste final para começar o jogo</li>
      <li>11.8 Correção de bugs que ficaram</li>
   </ul>

   <ul>12 Fechamento para o app
      <li>12.1 Launch Screen</li>
      <li>12.2 Ícone e nome para o app no dispositivo</li>
      <li>12.3 Responsividade</li>
   </ul>

   <ul>13 Os estudos não param por aqui</ul>',
'Aprofundando em Flutter: Desenvolva aplicações Dart com Widgets','4','2');

   INSERT IGNORE INTO `livro` (`id`, `capa`, `data_publicacao`, `isbn`, `numero_paginas`, `preco`, `sumario`, `titulo`, `autor_id`, `categoria_id`)
   VALUES ('9', 'https://cdn.shopify.com/s/files/1/0155/7645/products/ASP.NETCoreMVC__Amazon_large.jpg?v=1631571623',
 '2018-07-01', '978-65-86110-33-3', '260', '69,90',
 '<ul>1 A primeira aplicação ASP.NET Core MVC
      <li>1.1 Criação do projeto no Visual Studio 2019 Community</li>
      <li>1.2 Criando o controlador para Instituições de Ensino</li>
      <li>1.3 Criação da classe de domínio para Instituições de Ensino</li>
      <li>1.4 Implementação da interação da action Index com a visão</li>
      <li>1.5 O conceito de rotas do ASP.NET Core MVC</li>
      <li>1.6 Implementação da inserção de dados no controlador</li>
      <li>1.7 Implementação da alteração de dados no controlador</li>
      <li>1.8 Implementação da visualização de um único registro</li>
      <li>1.9 Finalização da aplicação: a operação Delete</li>
      <li>1.10 Conclusão sobre as atividades realizadas no capítulo</li>
   </ul>

   <ul>2 Acesso a dados com o Entity Framework Core
      <li>2.1 Começando com o Entity Framework Core</li>
      <li>2.2 Verificando a base de dados criada no Visual Studio</li>
      <li>2.3 Implementação do CRUD fazendo uso do Entity Framework Core</li>
      <li>2.4 Conclusão sobre as atividades realizadas no capítulo</li>
   </ul>

   <ul>3 Layouts, Bootstrap e jQuery DataTable
      <li>3.1 O Bootstrap</li>
      <li>3.2 Layouts com o Bootstrap</li>
      <li>3.3 Primeira página com o layout criado</li>
      <li>3.4 Adaptando as visões para o uso do Bootstrap</li>
      <li>3.5 Configuração do menu para acessar as visões criadas</li>
      <li>3.6 Conclusão sobre as atividades realizadas no capítulo</li>
   </ul>


   <ul>4 Associações no Entity Framework Core
      <li>4.1 Associando as classes já criadas</li>
      <li>4.2 Adaptação para uso das associações</li>
      <li>4.3 A visão Create para a classe Departamento</li>
      <li>4.4 A visão Edit para a classe Departamento</li>
      <li>4.5 A visão Details para a classe Departamento</li>
      <li>4.6 Criando a visão Delete para a classe Departamento</li>
      <li>4.7 Inserção de Departamentos na visão Details de Instituições</li>
      <li>4.8 Conclusão sobre as atividades realizadas no capítulo</li>
   </ul>

    <ul>5 Separação da camada de negócio
      <li>5.1 Contextualização sobre as camadas</li>
      <li>5.2 Criando a camada de negócio: o modelo</li>
      <li>5.3 Criando a camada de persistência em uma pasta da aplicação</li>
      <li>5.4 Adaptação da camada de aplicação</li>
      <li>5.5 O DAL para Departamento</li>
      <li>5.6 Adaptando as visões para minimizar redundâncias</li>
      <li>5.7 Conclusão sobre as atividades realizadas no capítulo</li>
   </ul>

    <ul>6 Code First Migrations, Data Annotations e validações
      <li>6.1 O uso do Code First Migrations</li>
      <li>6.2 Atualização do modelo de negócio</li>
      <li>6.3 O uso de validações</li>
      <li>6.4 Conclusão sobre as atividades realizadas no capítulo</li>
    </ul>

    <ul>7 Areas, autenticação e autorização
      <li>7.1 Areas
      <li>7.2 Segurança em aplicações ASP.NET MVC
      <li>7.3 Criação de um acesso autenticado
      <li>7.4 Registro de um novo usuário
      <li>7.5 Usuário autenticado e o seu logout
      <li>7.6 Conclusão sobre as atividades realizadas no capítulo
   </ul>

    <ul>8 Uploads, downloads e erros
      <li>8.1 Uploads</li>
      <li>8.2 Apresentação da imagem na visão Details</li>
      <li>8.3 Permitindo o download da imagem enviada</li>
      <li>8.4 Páginas de erro</li>
      <li>8.5 Conclusão sobre as atividades realizadas no capítulo</li>
   </ul>

    <ul>9 DropDownList com chamadas AJAX e uso de sessões
      <li>9.1 Criação e adaptação de classes para o registro de professores</li>
      <li>9.2 O controlador para professores</li>
      <li>9.3 A visão para o registro de professores</li>
      <li>9.4 Actions invocadas via AJAX/jQuery para atualização dos DropDownLists</li>
      <li>9.5 Armazenando valores na sessão</li>
      <li>9.6 Conclusão sobre as atividades realizadas no capítulo</li>
   </ul>

    <ul>10 Os estudos não param por aqui</ul>',
 'ASP.NET Core MVC: Aplicações modernas em conjunto com o Entity Framework', '4', '2');

    INSERT IGNORE INTO `livro` (`id`, `capa`, `data_publicacao`, `isbn`, `numero_paginas`, `preco`, `sumario`, `titulo`, `autor_id`, `categoria_id`)
   VALUES ('10', 'https://cdn.shopify.com/s/files/1/0155/7645/products/Ionic-6-DIGITAL__ebook_large.png?v=1643251131',
 '2020-05-01', '978-65-86110-96-8', '358', '79,90',
'  <ul>1 Dispositivos móveis, desenvolvimento cross-platform e Ionic
    <li>1.1 Os dispositivos móveis na atualidade</li>
    <li>1.2 O desenvolvimento móvel cross-platform</li>
    <li>1.3 O Ionic Framework</li>
   </ul>

    <ul>2 Ionic — Instalação e testes
      <li>2.1 Ferramentas, downloads e instalação</li>
      <li>2.2 Testando a instalação do ambiente com um projeto básico</li>
      <li>2.3 Execução de forma nativa no iOS</li>
      <li>2.4 Execução de forma nativa no Android</li>
      <li>2.5 Depuração</li>
    </ul>

    <ul>3 A primeira aplicação Ionic
      <li>3.1 A estrutura básica de um projeto Ionic</li>
      <li>3.2 Os componentes básicos do template home</li>
      <li>3.3 A execução da aplicação</li>
      <li>3.4 Criação da página de clientes</li>
      <li>3.5 Mudanças na interface com o usuário</li>
      <li>3.6 Recuperação dos dados informados</li>
      <li>3.7 Uso de binding de componentes e formulários</li>
      <li>3.8 Angular Reactive Forms</li>
      <li>3.9 Não exibir mensagens de erro antes da submissão e alteração</li>
      <li>3.10 Validação do Reactive Form no componente TypeScript</li>
      <li>3.11 Um componente específico para data</li>
      <li>3.12 Confirmando a inserção com o usuário</li>
   </ul>

    <ul>4 Criação e execução inicial do projeto
      <li>4.1 Populando a página com itens</li>
      <li>4.2 Navegação para uma página de detalhes de um tipo de serviço</li>
      <li>4.3 Recuperação do tipo de serviço selecionado</li>
      <li>4.4 Alteração dos dados exibidos na página de detalhes</li>
      <li>4.5 Navegação para uma página de inserção de dados utilizando classes de negócio</li>
      <li>4.6 Remoção de um item da lista</li>
   </ul>

    <ul>5 Persistindo dados fisicamente em aplicações Ionic
      <li>5.1 Criação dos artefatos de modelo e de registro</li>
      <li>5.2 Persistência no Ionic com Storage para a inserção de dados</li>
      <li>5.3 Recuperação de dados persistidos com Storage</li>
      <li>5.4 Recuperação de uma peça selecionada na listagem</li>
      <li>5.5 Implementação e validação das operações CRUD</li>
      <li>5.6 SQLite e Cordova como mecanismo de persistência</li>
      <li>5.7 A interface com o usuário para as Ordens de Serviço</li>
      <li>5.8 A aplicação em execução</li>
    </ul>

    <ul>6 Sidemenu, CRUD com SQLite e o componente Select
      <li>6.1 Criação do Sidemenu</li>
      <li>6.2 A tabela de clientes na base de dados</li>
      <li>6.3 Service para clientes</li>
      <li>6.4 A inserção de ordens de serviço</li>
      <li>6.5 Acesso a um atendimento existente</li>
      <li>6.6 Gravação dos dados do atendimento</li>
      <li>6.7 Removendo um atendimento já registrado</li>
   </ul>

    <ul>7 CRUD com Firestore para acesso a dados remoto e uma página de pesquisa
      <li>7.1 Preparação do projeto para o Firestore</li>
      <li>7.2 Populando a base de dados do Firestore</li>
      <li>7.3 Visualização dos clientes registrados</li>
      <li>7.4 Consulta e alteração de um cliente</li>
      <li>7.5 Visualização dos atendimentos registrados no Firestore</li>
      <li>7.6 A pesquisa de clientes para o atendimento</li>
      <li>7.7 A criação de um atendimento com acesso à pesquisa por clientes</li>
      <li>7.8 Eventos na comunicação entre componentes de visão</li>
      <li>7.9 O registro do atendimento com o cliente selecionado</li>
      <li>7.10 A alteração de um atendimento já registrado</li>
      <li>7.11 A remoção de um atendimento já registrado</li>
   </ul>

    <ul>8 Câmera e álbum de fotos no CRUD de clientes e Storage do Firebase
      <li>8.1 Adaptação de nossa página de clientes</li>
      <li>8.2 Interação com a câmera e o álbum de imagens</li>
      <li>8.3 Armazenando no dispositivo a imagem capturada</li>
      <li>8.4 Visualização das imagens dos clientes na listagem</li>
      <li>8.5 Remoção da imagem física do dispositivo</li>
      <li>8.6 Vamos manter a foto também na nuvem</li>
      <li>8.7 A remoção da imagem no Storage</li>
   </ul>

    <ul>9 Autenticação e criação de usuários no Firebase e uso de Tabs
      <li>9.1 Preparação do projeto para a autenticação</li>
      <li>9.2 O login para acessar a aplicação</li>
      <li>9.3 A aplicação acessível por Tabs</li>
      <li>9.4 A página para listagem de clientes registrados</li>
      <li>9.5 Proibindo o acesso anônimo</li>
      <li>9.6 Criação de novos usuários diretamente pela aplicação</li>
   </ul>

    <ul>10 Integração com Google Maps
      <li>10.1 Criando nossa aplicação no Google Cloud</li>
      <li>10.2 Preparação da aplicação para a visualização de mapas</li>
      <li>10.3 A aplicação em execução</li>
      <li>10.4 Inserção do mapa na aplicação</li>
      <li>10.5 Identificação da localização do dispositivo</li>
      <li>10.6 A busca por um endereço</li>
      <li>10.7 Ícone da aplicação e SplashScreen</li>
   </ul>

    <ul>11 Os estudos não param por aqui</ul>',
'Ionic 6: Desenvolvimento multiplataforma para dispositivos móveis', '4', '2');

    INSERT IGNORE INTO `livro` (`id`, `capa`, `data_publicacao`, `isbn`, `numero_paginas`, `preco`, `sumario`, `titulo`, `autor_id`, `categoria_id`)
   VALUES ('11', 'https://cdn.shopify.com/s/files/1/0155/7645/products/ASP.NETCoreMVC__Amazon_large.jpg?v=1631571623',
 '2018-02-01', '978-65-86110-33-3', '260', '69,90',
 '<ul>1 A primeira aplicação ASP.NET Core MVC
   <li>1.1 Criação do projeto no Visual Studio 2019 Community</li>
   <li>1.2 Criando o controlador para Instituições de Ensino</li>
   <li>1.3 Criação da classe de domínio para Instituições de Ensino</li>
   <li>1.4 Implementação da interação da action Index com a visão</li>
   <li>1.5 O conceito de rotas do ASP.NET Core MVC</li>
   <li>1.6 Implementação da inserção de dados no controlador</li>
   <li>1.7 Implementação da alteração de dados no controlador</li>
   <li>1.8 Implementação da visualização de um único registro</li>
   <li>1.9 Finalização da aplicação: a operação Delete</li>
   <li>1.10 Conclusão sobre as atividades realizadas no capítulo</li>
</ul>

<ul>2 Acesso a dados com o Entity Framework Core
   <li>2.1 Começando com o Entity Framework Core</li>
   <li>2.2 Verificando a base de dados criada no Visual Studio</li>
   <li>2.3 Implementação do CRUD fazendo uso do Entity Framework Core</li>
   <li>2.4 Conclusão sobre as atividades realizadas no capítulo</li>
</ul>

<ul>3 Layouts, Bootstrap e jQuery DataTable
   <li>3.1 O Bootstrap</li>
   <li>3.2 Layouts com o Bootstrap</li>
   <li>3.3 Primeira página com o layout criado</li>
   <li>3.4 Adaptando as visões para o uso do Bootstrap</li>
   <li>3.5 Configuração do menu para acessar as visões criadas</li>
   <li>3.6 Conclusão sobre as atividades realizadas no capítulo</li>
</ul>

<ul>4 Associações no Entity Framework Core
   <li>4.1 Associando as classes já criadas</li>
   <li>4.2 Adaptação para uso das associações</li>
   <li>4.3 A visão Create para a classe Departamento</li>
   <li>4.4 A visão Edit para a classe Departamento</li>
   <li>4.5 A visão Details para a classe Departamento</li>
   <li>4.6 Criando a visão Delete para a classe Departamento</li>
   <li>4.7 Inserção de Departamentos na visão Details de Instituições</li>
   <li>4.8 Conclusão sobre as atividades realizadas no capítulo</li>
</ul>

<ul>5 Separação da camada de negócio
   <li>5.1 Contextualização sobre as camadas</li>
   <li>5.2 Criando a camada de negócio: o modelo</li>
   <li>5.3 Criando a camada de persistência em uma pasta da aplicação</li>
   <li>5.4 Adaptação da camada de aplicação</li>
   <li>5.5 O DAL para Departamento</li>
   <li>5.6 Adaptando as visões para minimizar redundâncias</li>
   <li>5.7 Conclusão sobre as atividades realizadas no capítulo</li>
</ul>

<ul>6 Code First Migrations, Data Annotations e validações
   <li>6.1 O uso do Code First Migrations</li>
   <li>6.2 Atualização do modelo de negócio</li>
   <li>6.3 O uso de validações</li>
   <li>6.4 Conclusão sobre as atividades realizadas no capítulo</li>
</ul>

<ul>7 Areas, autenticação e autorização
   <li>7.1 Areas</li>
   <li>7.2 Segurança em aplicações ASP.NET MVC</li>
   <li>7.3 Criação de um acesso autenticado</li>
   <li>7.4 Registro de um novo usuário</li>
   <li>7.5 Usuário autenticado e o seu logout</li>
   <li>7.6 Conclusão sobre as atividades realizadas no capítulo</li>
</ul>

<ul>8 Uploads, downloads e erros
   <li>8.1 Uploads</li>
   <li>8.2 Apresentação da imagem na visão Details</li>
   <li>8.3 Permitindo o download da imagem enviada</li>
   <li>8.4 Páginas de erro</li>
   <li>8.5 Conclusão sobre as atividades realizadas no capítulo</li>
</ul>

<ul>9 DropDownList com chamadas AJAX e uso de sessões
   <li>9.1 Criação e adaptação de classes para o registro de professores</li>
   <li>9.2 O controlador para professores</li>
   <li>9.3 A visão para o registro de professores</li>
   <li>9.4 Actions invocadas via AJAX/jQuery para atualização dos DropDownLists</li>
   <li>9.5 Armazenando valores na sessão</li>
   <li>9.6 Conclusão sobre as atividades realizadas no capítulo</li>
</ul>

<ul>10 Os estudos não param por aqui</ul>',
 'ASP.NET Core MVC: Aplicações modernas em conjunto com o Entity Framework','4','2');

     INSERT IGNORE INTO `livro` (`id`, `capa`, `data_publicacao`, `isbn`, `numero_paginas`, `preco`, `sumario`, `titulo`, `autor_id`, `categoria_id`)
   VALUES ('12', 'https://cdn.shopify.com/s/files/1/0155/7645/products/ASP.NETMVC5__Amazon_large.jpg?v=1631571970',
 '2016-05-01', '978-85-5519-189-3', '259', '69,90',
  '<ul>1 A primeira aplicação ASP.NET MVC5
      <li>1.1 Criando o projeto no Visual Studio 2015 Community</li>
      <li>1.2 Criando o controlador para Categorias de produtos</li>
      <li>1.3 Criando a classe de domínio para Categorias de produtos</li>
      <li>1.4 Implementando a interação da action Index com a visão</li>
      <li>1.5 O conceito de rotas do ASP.NET MVC</li>
      <li>1.6 Implementando a inserção de dados no controlador</li>
      <li>1.7 Implementando a alteração de dados no controlador</li>
      <li>1.8 Implementando a visualização de um único registro</li>
      <li>1.9 Finalizando a aplicação por meio da implementação da operação Delete do CRUD</li>
      <li>1.10 Conclusão sobre as atividades realizadas no capítulo</li>
   </ul>

<ul>2 Realizando acesso a dados na aplicação ASP.NET MVC com o Entity Framework
   <li>2.1 Começando com o Entity Framework</li>
   <li>2.2 Implementando o CRUD fazendo uso do Entity Framework</li>
   <li>2.3 Conclusão sobre as atividades realizadas no capítulo</li>
</ul>

<ul>3 Layouts, Bootstrap e jQuery DataTable
   <li>3.1 O Bootstrap</li>
   <li>3.2 Layouts</li>
   <li>3.3 Adaptando as visões para o uso do Bootstrap</li>
   <li>3.4 Configurando o menu de acesso para destacar a página atual</li>
   <li>3.5 Conclusão sobre as atividades realizadas no capítulo</li>
</ul>

<ul>4 Associações no Entity Framework
   <li>4.1 Associando as classes já criadas a uma nova classe</li>
   <li>4.2 Criando a visão Index para a classe associada</li>
   <li>4.3 Inicializadores de contexto do Entity Framework</li>
   <li>4.4 Criando a visão Create para a classe Produto</li>
   <li>4.5 Criando a visão Edit para a classe Produto</li>
   <li>4.6 Criando a visão Details para a classe Produto</li>
   <li>4.7 Criando a visão Delete para a classe Produto</li>
   <li>4.8 Adaptando a visão Details de Fabricantes</li>
   <li>4.9 Conclusão sobre as atividades realizadas no capítulo</li>
</ul>

<ul>5 Separando a aplicação em camadas
   <li>5.1 Contextualização sobre as camadas</li>
   <li>5.2 Criando a camada de negócio ─ O modelo</li>
   <li>5.3 Criando a camada de persistência</li>
   <li>5.4 Criando a camada de serviço</li>
   <li>5.5 Adaptando a camada de aplicação</li>
   <li>5.6 Adaptando as visões para minimizar redundâncias</li>
   <li>5.7 Conclusão sobre as atividades realizadas no capítulo</li>
</ul>

<ul>6 Code First Migrations, Data Annotations, validações e jQueryUI
   <li>6.1 Fazendo uso do Code First Migrations</li>
   <li>6.2 Adaptando a classe Produto para as validações</li>
   <li>6.3 Testando as alterações implementadas</li>
   <li>6.4 Implementando o controle de data</li>
   <li>6.5 Fazendo uso do jQueryUI para controles de data</li>
   <li>6.6 Validação no lado cliente</li>
   <li>6.7 Conclusão sobre as atividades realizadas no capítulo</li>
</ul>

<ul>7 Areas, autenticação e autorização
   <li>7.1 Areas</li>
   <li>7.2 Segurança em aplicações ASP.NET MVC</li>
   <li>7.3 Listando os usuários registrados</li>
   <li>7.4 Criando usuários</li>
   <li>7.5 Alterando usuários já cadastrados</li>
   <li>7.6 Removendo um usuário existente</li>
   <li>7.7 Adaptando o menu da aplicação para as funcionalidades de usuários</li>
   <li>7.8 Restringindo o acesso a actions</li>
   <li>7.9 Implementando a autenticação</li>
   <li>7.10 Utilizando papéis (roles) na autorização</li>
   <li>7.11 Gerenciando membros de papéis</li>
   <li>7.12 Criando os acessos para login e logout</li>
   <li>7.13 Alterando o processo de autorização para utilizar papéis</li>
   <li>7.14 Conclusão sobre as atividades realizadas no capítulo</li>
</ul>

<ul>8 Uploads, downloads e erros
   <li>8.1 Uploads</li>
   <li>8.2 Download</li>
   <li>8.3 Páginas de erro</li>
   <li>8.4 Conclusão sobre as atividades realizadas no capítulo</li>
</ul>

<ul>9 Um carrinho de compras
   <li>9.1 Adição do carrinho de compra ao modelo de negócio</li>
   <li>9.2 O controlador para o carrinho de compra</li>
   <li>9.3 A visão para o carrinho de compra</li>
   <li>9.4 Registrando o produto no carrinho de compra</li>
   <li>9.5 O que falta para terminar o carrinho?</li>
   <li>9.6 Conclusão sobre as atividades realizadas no capítulo</li>
</ul>

<ul>10 Uso de DropDownList aninhado, RadioButton e CheckBox
   <li>10.1 Implementações necessárias para as classes fornecedoras dos DropDownLists</li>
   <li>10.2 Adaptações nas implementações existentes para o DropDownList</li>
   <li>10.3 Inserção de um controle RadioButton em Fabricantes</li>
   <li>10.4 Inserção de um controle CheckBox em Fabricantes</li>
   <li>10.5 Conclusão sobre as atividades realizadas no capítulo</li>
</ul>

<ul>11 Os estudos não param por aqui</ul>',
 'ASP.NET MVC5: Crie aplicações web na plataforma Microsoft','4','2');
