package com.example.todoapp;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

public enum Categoria {
    PESSOAL(R.string.categoria_pessoal, R.color.categoria_pessoal, R.drawable.ic_person),
    TRABALHO(R.string.categoria_trabalho, R.color.categoria_trabalho, R.drawable.ic_work),
    ESTUDOS(R.string.categoria_estudos, R.color.categoria_estudos, R.drawable.ic_school),
    COMPRAS(R.string.categoria_compras, R.color.categoria_compras, R.drawable.ic_shopping_cart);

    @StringRes
    private final int nomeResId;
    @ColorRes
    private final int corResId;
    @DrawableRes
    private final int iconeResId;

    Categoria(@StringRes int nomeResId, @ColorRes int corResId, @DrawableRes int iconeResId) {
        this.nomeResId = nomeResId;
        this.corResId = corResId;
        this.iconeResId = iconeResId;
    }

    public int getNomeResId() {
        return nomeResId;
    }

    public int getCorResId() {
        return corResId;
    }

    public int getIconeResId() {
        return iconeResId;
    }
}
