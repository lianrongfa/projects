package com.lianrf.tierexp;

import com.lianrf.tierexp.context.ExpContext;
import com.lianrf.tierexp.parser.TierExpBaseVisitor;
import com.lianrf.tierexp.parser.TierExpParser;

/**
 * TierVisitor
 *
 * @author lianrf
 * @version 1.0
 * @since 2022/3/2 4:40 下午
 */
public class TierVisitor extends TierExpBaseVisitor<Object> {

    private ExpContext<String,Object> context;

    @Override
    public Object visitExprLiterals(TierExpParser.ExprLiteralsContext ctx) {
        return ctx.interpreter.interpret(context,ctx,this);
    }

    @Override
    public Object visitExprAddSub(TierExpParser.ExprAddSubContext ctx) {
        return ctx.interpreter.interpret(context,ctx,this);
    }

    @Override
    public Object visitExprMultDiv(TierExpParser.ExprMultDivContext ctx) {
        return ctx.interpreter.interpret(context,ctx,this);
    }

    @Override
    public Object visitExprId(TierExpParser.ExprIdContext ctx) {
        return ctx.interpreter.interpret(context,ctx,this);
    }

    @Override
    public Object visitExprParens(TierExpParser.ExprParensContext ctx) {
        return ctx.interpreter.interpret(context,ctx,this);
    }

    @Override
    public Object visitExprAttr(TierExpParser.ExprAttrContext ctx) {
        return ctx.interpreter.interpret(context,ctx,this);
    }

    @Override
    public Object visitExprCall(TierExpParser.ExprCallContext ctx) {
        return ctx.interpreter.interpret(context,ctx,this);
    }

    @Override
    public Object visitExprNot(TierExpParser.ExprNotContext ctx) {
        return ctx.interpreter.interpret(context,ctx,this);
    }

    @Override
    public Object visitExprNegate(TierExpParser.ExprNegateContext ctx) {
        return ctx.interpreter.interpret(context,ctx,this);
    }

    @Override
    public Object visitExprMod(TierExpParser.ExprModContext ctx) {
        return ctx.interpreter.interpret(context,ctx,this);
    }

    public ExpContext<String, Object> getContext() {
        return context;
    }

    public void setContext(ExpContext<String, Object> context) {
        this.context = context;
    }
}
