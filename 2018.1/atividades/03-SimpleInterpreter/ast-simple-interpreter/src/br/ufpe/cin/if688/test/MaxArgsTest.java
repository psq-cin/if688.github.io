package br.ufpe.cin.if688.test;

import br.ufpe.cin.if688.visitor.MaxArgsVisitor;
import junit.framework.TestCase;

public class MaxArgsTest extends TestCase {

	public void testG1() {
		MaxArgsVisitor maxArgs = new MaxArgsVisitor();

		int result = maxArgs.visit(Prog.prog);

		assertEquals(2, result);
	}

	public void testG2() {
		MaxArgsVisitor maxArgs = new MaxArgsVisitor();

		int result = maxArgs.visit(Prog.prog2);

		assertEquals(1, result);
	}

	public void testG3() {
		MaxArgsVisitor maxArgs = new MaxArgsVisitor();

		int result = maxArgs.visit(Prog.print1234);

		assertEquals(5, result);
	}

	public void testG4() {
		MaxArgsVisitor maxArgs = new MaxArgsVisitor();

		int result = maxArgs.visit(Prog.printPrint);

		assertEquals(5, result);
	}

	public void testG5() {
		MaxArgsVisitor maxArgs = new MaxArgsVisitor();

		int result = maxArgs.visit(Prog.print);

		assertEquals(1, result);
	}

	public void testG6() {
		MaxArgsVisitor maxArgs = new MaxArgsVisitor();

		int result = maxArgs.visit(Prog.prog3);

		assertEquals(0, result);
	}
	// esses 7 e 8 foram adicionados por mim para conferir se meu programa bate
	// com o que eu penso :)
	public void testG7() {
		MaxArgsVisitor maxArgs = new MaxArgsVisitor();

		int result = maxArgs.visit(Prog.printEseqExp);

		assertEquals(1, result);
	}
	
	public void testG8() {
		MaxArgsVisitor maxArgs = new MaxArgsVisitor();

		int result = maxArgs.visit(Prog.progEseqExp);

		assertEquals(2, result);
	}

}
