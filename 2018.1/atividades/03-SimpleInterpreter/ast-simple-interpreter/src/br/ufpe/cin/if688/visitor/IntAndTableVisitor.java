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

public class IntAndTableVisitor implements IVisitor<IntAndTable> {
	private Table t;

	public IntAndTableVisitor(Table t) {
		this.t = t;
	}

	@Override
	public IntAndTable visit(Stm s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntAndTable visit(AssignStm s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntAndTable visit(CompoundStm s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntAndTable visit(PrintStm s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntAndTable visit(Exp e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntAndTable visit(EseqExp e) {
		t = e.getStm().accept(new Interpreter(t));
		double r = e.getExp().accept(this).result;
		IntAndTable iat = new IntAndTable(r, t);
		return iat;
	}

	@Override
	public IntAndTable visit(IdExp e) {
		String id = e.getId();
		double value = 0;
		Table iterTable = t;
		while (iterTable != null) {
			if (iterTable.id == id) {
				value = iterTable.value;
				break;
			} else {
				iterTable = iterTable.tail;
			}
		}
		if (iterTable == null) return null;
		IntAndTable iat = new IntAndTable(value, t);
		return iat;
	}

	@Override
	public IntAndTable visit(NumExp e) {
		IntAndTable iat = new IntAndTable(e.getNum(), e.accept(new Interpreter(t)));
		return iat;
	}

	@Override
	public IntAndTable visit(OpExp e) {
		double l = e.getLeft().accept(this).result;
		double r = e.getRight().accept(this).result;
		double ret = 8008135;
		switch (e.getOper()) {
		case 1: // +
			ret = l + r;
			break;
		case 2: // -
			ret = l - r;
			break;
		case 3: // *
			ret = l * r;
			break;
		case 4: // /
			ret = l / r;
			break;	
		}
		IntAndTable iat = new IntAndTable(ret, t);
		return iat;
	}

	@Override
	public IntAndTable visit(ExpList el) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntAndTable visit(PairExpList el) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntAndTable visit(LastExpList el) {
		// TODO Auto-generated method stub
		return null;
	}


}
