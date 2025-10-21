# GerenciadorEscolarJava

 Gerenciador Escolar Java

 * Integrante
- Pietro
//(O que o programa faz)
 * Visão Geral
Projeto desenvolvido para praticar o uso das coleções Java (List, Set e Map), dividido em quatro partes principais:
- Estudantes
- Disciplinas
- Matrículas e notas

// (Wireframe)
️ * Estrutura do Projeto

| Tipo      | Classe               | Responsabilidade                                |
|-----------|----------------------|-------------------------------------------------|
| 🧍        | Estudante            | Guarda id e nome                                |
| 📘        | Disciplina           | Guarda código e nome                            |
| 🧾        | Matricula            | Guarda códigoDisciplina e nota                  |
| 📋        | ListaEstudantes      | Manipula lista de estudantes (Parte A)          |
| 📚        | CadastroDisciplinas  | Manipula conjunto de disciplinas (Parte B)      |
| 🗺️        | HistoricoNotas       | Liga estudantes, disciplinas e notas (Parte C)  |
| ▶️        | Main                 | Executa o programa e mostra resultados (Parte D)|

*   Parte A — List (ArrayList ou LinkedList)
Manipula estudantes: adiciona, remove, ordena e busca estudantes na lista.

* Parte B — Set (HashSet, LinkedHashSet ou TreeSet)
Manipula disciplinas: garante que não haja disciplinas duplicadas, verifica, remove e lista disciplinas.

* Parte C — Map (HashMap)
Associa estudantes às disciplinas e notas: guarda histórico de matrículas, obtém notas, remove e calcula médias, lista melhores alunos.

 * Parte D — Main (Integração)
Executa todo o fluxo:
- Criação de exemplos de estudantes, disciplinas e matrículas
- Relatórios: estudantes ordenados, disciplinas, médias e destaques
- Cálculo de:
    - Alunos com média ≥ 8.0
    - Disciplinas com média < 6.0
    - Top N alunos por média
    - Duplicatas de disciplinas

 * Abra o terminal (Prompt de Comando, PowerShell, Terminal do IntelliJ).

- Navegue até a pasta do projeto:
cd CAMINHO/DA/SUA/PASTA

- Compile os arquivos:
javac escola/*.java

- Execute normalmente para testar:
java escola.Main

* Conteúdo do output.txt

O arquivo output.txt é o principal relatório gerado pelo programa e contém 
o resultado de todas as operações de relatórios e cálculos definidos na Parte D (Main - Integração).

* O conteúdo do arquivo inclui:

Relatório de Estudantes Ordenados: A lista completa de estudantes, 
provavelmente ordenada alfabeticamente ou por ID.
Relatório de Disciplinas: A lista de todas as disciplinas cadastradas.

Cálculo de Médias e Destaques:
Média geral de todos os alunos.
Lista de alunos com média final \geq 8.0.
Lista de disciplinas com média de aprovação < 6.0.

O Top N (ou Top 3, por exemplo) alunos com as melhores médias.

Verificação de Duplicatas: Resultados da tentativa de inserção de disciplinas
ou estudantes duplicados (Parte A e B).

 Desafios Encontrados
O principal desafio encontrado foi a modelagem e a integração dos dados na Parte C (HistoricoNotas). 
 Foi complexo definir uma estrutura de Map que permitisse associar corretamente o Estudante (chave) a uma 
 lista de suas Matrículas (valor), que continham as notas. Além disso, a iplementação dos
 métodos de cálculo de Médias Ponderaadas e listagem de Top Alunos exigiu uma navegação coordenada entre
 as três coleções (List, Set e Map) para garantir a precisão dos relatórios finais.
 ]
