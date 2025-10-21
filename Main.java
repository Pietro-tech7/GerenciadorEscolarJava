package escola;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {

        ListaEstudante listaEstudante = new ListaEstudante();
        Estudante ana = new Estudante(1, "Ana");
        Estudante bruno = new Estudante(2, "Bruno");
        Estudante carla = new Estudante(3, "Carla");
        Estudante diego = new Estudante(4, "Diego");
        Estudante elisa = new Estudante(5, "Elisa");

        listaEstudante.adicionarEstudante(ana);
        listaEstudante.adicionarEstudante(bruno);
        listaEstudante.adicionarEstudante(carla);
        listaEstudante.adicionarEstudante(diego);
        listaEstudante.adicionarEstudante(elisa);


        ana.adicionarReceita(new Receita("Bolo de Chocolate", "chocolate, farinha, açúcar, ovos"));
        bruno.adicionarReceita(new Receita("Bolo de Cenoura", "cenoura, farinha, açúcar, ovos"));
        carla.adicionarReceita(new Receita("Bolo de Fubá", "fubá, leite, farinha, ovos"));
        diego.adicionarReceita(new Receita("Bolo de Milho", "milho, farinha, açúcar, ovos"));
        elisa.adicionarReceita(new Receita("Bolo Gelado", "leite condensado, coco, farinha, ovos"));


        CadastroDisciplinas cadastroDisciplinas = new CadastroDisciplinas();
        List<String> duplicadas = new ArrayList<>(); // Para rastrear duplicatas
        Disciplina[] disciplinas = {
                new Disciplina("MAT101", "Matemática"),
                new Disciplina("PRG201", "Programação"),
                new Disciplina("BD301", "Banco de Dados"),
                new Disciplina("EDF110", "Educação Física"),
                new Disciplina("MAT101", "Matemática II") // Duplicada (não será adicionada)
        };

        for (Disciplina d : disciplinas) {
            boolean added = cadastroDisciplinas.adicionarDisciplina(d);
            if (!added) duplicadas.add(d.getCodigo());
        }

        HistoricoNotas historico = new HistoricoNotas();
        historico.adicionarMatricula(1, "MAT101", 8.5);   // Ana
        historico.adicionarMatricula(1, "PRG201", 9.0);   // Ana
        historico.adicionarMatricula(2, "PRG201", 7.0);   // Bruno
        historico.adicionarMatricula(3, "BD301", 6.5);    // Carla
        historico.adicionarMatricula(4, "PRG201", 8.0);   // Diego
        historico.adicionarMatricula(5, "EDF110", 10.0);  // Elisa


        historico.adicionarMatricula(2, "MAT101", 5.0);   // Bruno
        historico.adicionarMatricula(3, "MAT101", 7.5);   // Carla


        System.out.println("== Lista de Estudantes (ordem de cadastro) ==");
        for (Estudante e : listaEstudante.obterTodos()) System.out.println(e);

        System.out.println("\n== Lista de Estudantes (ordenada) ==");
        listaEstudante.ordenarEstudantesPorNome();
        List<Estudante> estOrdenados = listaEstudante.obterTodos();
        for (int i = 0; i < estOrdenados.size(); i++) {
            System.out.print(estOrdenados.get(i).getNome());
            if (i < estOrdenados.size() - 1) System.out.print(", ");
        }

        System.out.println("\n\n== Disciplinas (inserção) ==");
        List<Disciplina> listaDisc = new ArrayList<>(cadastroDisciplinas.obterTodasDisciplinas());
        for (int i = 0; i < listaDisc.size(); i++) {
            System.out.print(listaDisc.get(i).getCodigo());
            if (i < listaDisc.size() - 1) System.out.print(", ");
        }

        System.out.println("\n\n== Duplicatas detectadas na importação ==");
        if (duplicadas.isEmpty())
            System.out.println("(nenhuma)");
        else
            System.out.println(String.join(", ", duplicadas));

        System.out.println("\n== Matrículas ==");
        for (Estudante e : listaEstudante.obterTodos()) {
            List<Matricula> mats = historico.obterMatriculas(e.getId());
            if (!mats.isEmpty()) {
                System.out.print(e.getNome() + ": ");
                for (int i = 0; i < mats.size(); i++) {
                    Matricula m = mats.get(i);
                    System.out.print(m.getCodigoDisciplina() + "(" + m.getNota() + ")");
                    if (i < mats.size() - 1) System.out.print(", ");
                }
                double media = historico.mediaDoEstudante(e.getId());
                System.out.println(" Média: " + String.format(Locale.US, "%.2f", media));
                // Extra: receitas associadas
                if (!e.getReceitas().isEmpty())
                    System.out.println("  Receita favorita: " + e.getReceitas().getFirst());
            }
        }

        System.out.println("\n== Médias por Disciplina ==");
        for (Disciplina d : cadastroDisciplinas.obterTodasDisciplinas()) {
            System.out.println(d.getCodigo() + ": " + String.format(Locale.US, "%.1f", historico.mediaDaDisciplina(d.getCodigo())));
        }

        System.out.println("\n== Top 3 alunos por média ==");
        List<Integer> top = historico.topNEstudantesPorMedia(3);
        for (int i = 0; i < top.size(); i++) {
            final int idDoEstudante = top.get(i);
            Estudante e = listaEstudante.obterTodos().stream().filter(est -> est.getId() == idDoEstudante).findFirst().orElse(null);
            if (e != null)
                System.out.println((i + 1) + ") " + e.getNome() + " - " + String.format(Locale.US, "%.2f", historico.mediaDoEstudante(e.getId())));
        }

        System.out.println("\n== Alunos com média >= 8.0 ==");
        boolean algum = false;
        for (Estudante e : listaEstudante.obterTodos()) {
            if (historico.mediaDoEstudante(e.getId()) >= 8.0) {
                System.out.print(e.getNome() + ", ");
                algum = true;
            }
        }
        if (!algum) System.out.print("(nenhum)");
        System.out.println();

        System.out.println("\n== Disciplinas com média < 6.0 ==");
        algum = false;
        for (Disciplina d : cadastroDisciplinas.obterTodasDisciplinas()) {
            if (historico.mediaDaDisciplina(d.getCodigo()) < 6.0) {
                System.out.println(d.getCodigo() + ", " + d.getNome());
                algum = true;
            }
        }
        if (!algum) System.out.println("  (ESSAS)");
        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader("output.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();

        }
    }
}
