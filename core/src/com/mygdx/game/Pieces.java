package com.mygdx.game;

import java.util.ArrayList;

public class Pieces
{

    ArrayList<Block> totalPieces;
    Block tile;
    Block person;

    public Pieces(Block tile, Block person)
    {
        this.tile = tile;
        this.tile = person;
        totalPieces = new ArrayList<>();
        totalPieces.add(tile);
        totalPieces.add(person);
    }

    public ArrayList<Block> getTotalPieces()
    {
        return totalPieces;
    }

    public void setTotalPieces(ArrayList<Block> totalPieces)
    {
        this.totalPieces = totalPieces;
    }

    public Block getTile()
    {
        return tile;
    }

    public void setTile(Block tile)
    {
        this.tile = tile;
    }

    public Block getPerson()
    {
        return person;
    }

    public void setPerson(Block person)
    {
        this.person = person;
    }
}
