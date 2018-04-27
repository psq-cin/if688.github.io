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
import br.ufpe.cin.if688.symboltable.IntAndTable;
import br.ufpe.cin.if688.symboltable.Table;

public class Interpreter implements IVisitor<Table> {
	
	//a=8;b=80;a=7;
	// a->7 ==> b->80 ==> a->8 ==> NIL
	private Table t;
	
	public Interpreter(Table t) {
		this.t = t;
	}

	@Override
	public Table visit(Stm s) {
		// TODO Auto-generated method stub
		return s.accept(this);
	}

	@Override
	public Table visit(AssignStm s) {
		String id = s.getId();
		Exp e = s.getExp();
		IntAndTable iat = e.accept(new IntAndTableVisitor(t));
		double resultado = iat.result;
		this.t = new Table(id, resultado, iat.table);
		return t;
	}

	@Override
	public Table visit(CompoundStm s) {
		s.getStm1().accept(this);
		s.getStm2().accept(this);
		return t;
	}

	@Override
	public Table visit(PrintStm s) {
		ExpList el, el1;
		el = el1 = s.getExps();
		while (el1 instanceof PairExpList) {
			Exp e = ((PairExpList) el1).getHead();
			IntAndTable it = e.accept(new IntAndTableVisitor(t));
			this.t= it.table;
			System.out.println(it.result);
			el1 = ((PairExpList) el1).getTail();
		}
		LastExpList le = (LastExpList) el1;
		Exp e = le.getHead();
		IntAndTable it = e.accept(new IntAndTableVisitor(t));
		this.t = it.table;
		System.out.println(it.result);
		
		el.accept(this);
		return t;
	}

	@Override
	public Table visit(Exp e) {
		return e.accept(this);
	}

	@Override
	public Table visit(EseqExp e) {
		//e.getExp().accept(this);
		//e.getStm().accept(this);
		return t;
	}

	@Override
	public Table visit(IdExp e) {
		return t;
	}

	@Override
	public Table visit(NumExp e) {
		return t;
	}

	@Override
	public Table visit(OpExp e) {
		//IntAndTable iat = e.accept(new IntAndTableVisitor(t));
		return t;
	}

	@Override
	public Table visit(ExpList el) {
		//el.accept(this);
		return t;
	}

	@Override
	public Table visit(PairExpList el) {
		//el.getHead().accept(this);
		//el.getTail().accept(this);
		return t;
	}

	@Override
	public Table visit(LastExpList el) {
		//el.getHead().accept(this);
		return t;
	}


}
