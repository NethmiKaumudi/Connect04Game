package lk.ijse.dep.service;

public class BoardImpl implements Board {
    private Piece[][] pieces;
    private BoardUI boardUI;
    //private int Col;

    public BoardImpl(BoardUI boardUI){
        this.boardUI=boardUI;
        pieces=new Piece[NUM_OF_COLS][NUM_OF_ROWS];
        //yet all pieces of the array are null.so we initialize all pieces with piece.empty
        for (int c = 0; c<pieces.length; c++) {
            for (int r = 0; r < pieces[c].length;r++) {
                pieces[c][r] = Piece.EMPTY;
            }
        }
    }

    @Override
    public BoardUI getBoardUI() {
        return boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {
    //this return how many columns and raws are filled
        int count = 5;

        for (int c= 0; c< pieces[c].length;c++) {
            if(pieces[col][c] == Piece.EMPTY) {
                count--;
            }
        }
    //if there any coloumn or row is not empty then return -1
        if (count == 5) {
            count =-1;
        }
        return count;
    }


    @Override
    public boolean isLegalMove(int col) {
        //check our move is legal or not
        boolean l=true;
        int count=findNextAvailableSpot(col);
        if(count==-1)l=false;
        return l;

    }

    @Override
    public boolean existLegalMoves() {
        //check is there any space to move and is that legal
        boolean legal = false;

        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                if (pieces[i][j] == Piece.EMPTY) {
                    legal = true;
                }
            }
        }

        return legal;
    }

    @Override
    public void updateMove(int col, Piece move) {

        pieces[col][findNextAvailableSpot(col)]=move;

    }

    @Override
    public Winner findWinner() {
        //if four rows have the same colour then can find winner
        Piece win = Piece.EMPTY;
        int c1 = 0;
        int c2 = 0;
        int r1 = 0;
        int r2 = 0;

        for (int c= 0; c< pieces.length; c++) {
            if (findNextAvailableSpot(c) == 4 || findNextAvailableSpot(c) == -1) { //
                if (pieces[c][0] == pieces[c][1] && pieces[c][1] == pieces[c][2] && pieces[c][2] == pieces[c][3]){
                    win = pieces[c][0];
                    c1 =c;
                    c2 =c;
                    r1 = 0;
                    r2 = 3;
                } else if (pieces[c][1] == pieces[c][2] && pieces[c][2] == pieces[c][3] && pieces[c][3] == pieces[c][4]) {
                    win = pieces[c][1];
                    c1 =c;
                    c2 =c;
                    r1 = 1;
                    r2 = 4;
                }
            }
        }
    //if four coloumns have same colour then can find winner
        for (int r= 0;r < pieces[r] .length;r++ ) {
            if (findAvailableRaws(r)== 4 || findAvailableRaws(r)== 5 || findAvailableRaws(r)== -1);
            if (pieces[0][r] == pieces[1][r] && pieces[1][r] == pieces[2][r] && pieces[2][r] == pieces[3][r]) {
                win = pieces[0][r];
                c1 = 0;
                c2 = 3;
                r1 =r;
                r2 =r;
            } else if (pieces[1][r] == pieces[2][r] && pieces[2][r] == pieces[3][r] && pieces[3][r]== pieces[4][r]) {
                win = pieces[1][r];
                c1 = 1;
                c2 = 4;
                r1 = r;
                r2 = r;
            } else if (pieces[2][r] == pieces[3][r] && pieces[3][r] == pieces[4][r] && pieces[4][r] == pieces[5][r]) {
                win = pieces[2][r];
                c1 = 2;
                c2 = 5;
                r1 = r;
                r2 = r;
            }
        }
        Winner winner;
        if (win == Piece.EMPTY) {
            winner = new Winner(win);
        } else {
            winner = new Winner(win, c1, r1, c2, r2);
        }

        return winner;

    }
    private int findAvailableRaws(int row) {
        int count=6;
        for(int i=0; i<pieces.length;i++){
            if(pieces[i][row]==Piece.EMPTY){
                count--;
            }
        }
        if(count==6){
            count=-1;
        }
        return count;

    }
}






