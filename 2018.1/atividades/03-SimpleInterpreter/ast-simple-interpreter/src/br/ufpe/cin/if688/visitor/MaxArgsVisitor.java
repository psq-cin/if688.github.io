package br.ufpe.cin.if688.visitor;

import br.ufpe.cin.if688.ast.AssignStm;
import br.ufpe.cin.if688.ast.CompoundStm;
import br.ufpe.cin.if688.ast.EseqExp;
import br.ufpe.cin.if688.ast.Exp;
import br.ufpe.cin.if688.ast.ExpList;
import br.ufpe.cin.if688.ast.IdExp;
import br.ufpe.cin.if688.ast.LastExpList;
import br.ufpe.cin.if688.ast.NumExp;
import br.ufpe.cin.if688.ast.OpExp;
import br.ufpe.cin.if688.ast.PairExpList;
import br.ufpe.cin.if688.ast.PrintStm;
import br.ufpe.cin.if688.ast.Stm;

public class MaxArgsVisitor implements IVisitor<Integer> {

	@Override
	public Integer visit(Stm s) {
		return s.accept(this);
	}

	@Override
	public Integer visit(AssignStm s) {
		return s.getExp().accept(this);
	}

	@Override
	public Integer visit(CompoundStm s) {
		int esq = s.getStm1().accept(this);
		int dir = s.getStm2().accept(this);
		return Math.max(esq, dir);
	}

	@Override
	public Integer visit(PrintStm s) {

		return s.getExps().accept(this);
	}

	@Override
	public Integer visit(Exp e) {
		// TODO Auto-generated method stub
		return e.accept(this);
	}

	@Override
	public Integer visit(EseqExp e) {
		int exp = e.getExp().accept(this);
		int stm = e.getStm().accept(this); // basicamente sempre 0 pela logica do eseq se eu entendi
		return Math.max(exp, stm);
	}

	@Override
	public Integer visit(IdExp e) {
		return 0;
	}

	@Override
	public Integer visit(NumExp e) {
		return 0;
	}

	@Override
	public Integer visit(OpExp e) {
		int esq = e.getLeft().accept(this);
		int dir = e.getRight().accept(this);
		return Math.max(esq, dir);
	}

	@Override
	public Integer visit(ExpList el) {
		
		return el.accept(this);
	}

	@Override
	public Integer visit(PairExpList el) {
		int dedentro = el.getHead().accept(this);
		int resto = 1 + el.getTail().accept(this);
		
		return Math.max(dedentro, resto);
	}

	@Override
	public Integer visit(LastExpList el) {
		
		return Math.max(1, el.getHead().accept(this));
	}

}
