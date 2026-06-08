error id: file:///C:/Users/Lucas/Documents/trabalho/codes/poo-pbl-grupo-temp/src/main/java/domain/value_objects/Dinheiro.java
file:///C:/Users/Lucas/Documents/trabalho/codes/poo-pbl-grupo-temp/src/main/java/domain/value_objects/Dinheiro.java
### com.thoughtworks.qdox.parser.ParseException: syntax error @[19,12]

error in qdox parser
file content:
```java
offset: 426
uri: file:///C:/Users/Lucas/Documents/trabalho/codes/poo-pbl-grupo-temp/src/main/java/domain/value_objects/Dinheiro.java
text:
```scala
package domain.value_objects;

import java.util.Objects;

/**
 * Value Object que representa uma quantia de dinheiro em centavos.
 *

public final class Dinheiro {

    private final int centavos;

    /**
     * Cria um Dinheiro a partir de um valor em centavos.
     *
     * @param centavos valor inteiro em centavos (ex: 1050 = R$ 10,50)
     * @throws IllegalArgumentException se o valor for negativo
     */
    public D@@inheiro(int centavos) {
        if (centavos < 0) {
            throw new IllegalArgumentException(
                "Valor não pode ser negativo: " + centavos
            );
        }
        this.centavos = centavos;
    }

    /**
     * Retorna o valor em centavos.
     
     *      * @return
     */
    public int getCentavos() {
        return centavos;
    }

    /**
     * Soma este Dinheiro com outro.
     *
     * @param outro 
     * @return 
     */
    public Dinheiro somar(Dinheiro outro) {
        if (outro == null) {
            throw new IllegalArgumentException("Não é possível somar com null");
        }
        return new Dinheiro(this.centavos + outro.centavos);
    }

    /**
     * Subtrai outro Dinheiro deste.
     *
     * @param outro Dinheiro a ser subtraído
     * @return 
     * @throws IllegalArgumentException 
     */
    public Dinheiro subtrair(Dinheiro outro) {
        if (outro == null) {
            throw new IllegalArgumentException("Não é possível subtrair null");
        }
        int resultado = this.centavos - outro.centavos;
        if (resultado < 0) {
            throw new IllegalArgumentException(
                "Resultado da subtração não pode ser negativo: " + resultado
            );
        }
        return new Dinheiro(resultado);
    }

    /**
     * Divide este Dinheiro igualmente entre N partes, sem perder centavos.
     *
     * A sobra (resto da divisão) vai para a primeira parte.
     * Ex: R$ 10,00 / 3 = [R$ 3,34, R$ 3,33, R$ 3,33]
     * (334 + 333 + 333 = 1000 centavos — sem perda!)
     *
     * @param numPartes número de partes para dividir
     * @return array de Dinheiro com o resultado da divisão
     * @throws IllegalArgumentException se numPartes for menor ou igual a zero
     */
    public Dinheiro[] dividir(int numPartes) {
        if (numPartes <= 0) {
            throw new IllegalArgumentException(
                "Número de partes deve ser maior que zero: " + numPartes
            );
        }

        int valorBase = this.centavos / numPartes;
        int sobra     = this.centavos % numPartes;

        Dinheiro[] partes = new Dinheiro[numPartes];

        // Primeira parte recebe a sobra para não perder centavos
        partes[0] = new Dinheiro(valorBase + sobra);

        for (int i = 1; i < numPartes; i++) {
            partes[i] = new Dinheiro(valorBase);
        }

        return partes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dinheiro dinheiro = (Dinheiro) o;
        return centavos == dinheiro.centavos;
    }

    @Override
    public int hashCode() {
        return Objects.hash(centavos);
    }

    @Override
    public String toString() {
        return String.format("R$ %.2f", centavos / 100.0);
    }
}

```

```



#### Error stacktrace:

```
com.thoughtworks.qdox.parser.impl.Parser.yyerror(Parser.java:2025)
	com.thoughtworks.qdox.parser.impl.Parser.yyparse(Parser.java:2147)
	com.thoughtworks.qdox.parser.impl.Parser.parse(Parser.java:2006)
	com.thoughtworks.qdox.library.SourceLibrary.parse(SourceLibrary.java:232)
	com.thoughtworks.qdox.library.SourceLibrary.parse(SourceLibrary.java:190)
	com.thoughtworks.qdox.library.SourceLibrary.addSource(SourceLibrary.java:94)
	com.thoughtworks.qdox.library.SourceLibrary.addSource(SourceLibrary.java:89)
	com.thoughtworks.qdox.library.SortedClassLibraryBuilder.addSource(SortedClassLibraryBuilder.java:162)
	com.thoughtworks.qdox.JavaProjectBuilder.addSource(JavaProjectBuilder.java:174)
	scala.meta.internal.mtags.JavaMtags.indexRoot(JavaMtags.scala:49)
	scala.meta.internal.metals.SemanticdbDefinition$.foreachWithReturnMtags(SemanticdbDefinition.scala:99)
	scala.meta.internal.metals.Indexer.indexSourceFile(Indexer.scala:560)
	scala.meta.internal.metals.Indexer.$anonfun$reindexWorkspaceSources$3(Indexer.scala:691)
	scala.meta.internal.metals.Indexer.$anonfun$reindexWorkspaceSources$3$adapted(Indexer.scala:688)
	scala.collection.IterableOnceOps.foreach(IterableOnce.scala:630)
	scala.collection.IterableOnceOps.foreach$(IterableOnce.scala:628)
	scala.collection.AbstractIterator.foreach(Iterator.scala:1313)
	scala.meta.internal.metals.Indexer.reindexWorkspaceSources(Indexer.scala:688)
	scala.meta.internal.metals.MetalsLspService.$anonfun$onChange$2(MetalsLspService.scala:940)
	scala.runtime.java8.JFunction0$mcV$sp.apply(JFunction0$mcV$sp.scala:18)
	scala.concurrent.Future$.$anonfun$apply$1(Future.scala:691)
	scala.concurrent.impl.Promise$Transformation.run(Promise.scala:500)
	java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1144)
	java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:642)
	java.base/java.lang.Thread.run(Thread.java:1583)
```
#### Short summary: 

QDox parse error in file:///C:/Users/Lucas/Documents/trabalho/codes/poo-pbl-grupo-temp/src/main/java/domain/value_objects/Dinheiro.java