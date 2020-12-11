package com.grosalex.chesslogger.models

import com.grosalex.chesslogger.R

interface Key {
    val notationStringRes: Int
}

enum class Piece : Key {
    KING {
        override val notationStringRes: Int
            get() = R.string.king_notation
    },
    QUEEN {
        override val notationStringRes: Int
            get() = R.string.queen_notation
    },
    BISHOP {
        override val notationStringRes: Int
            get() = R.string.bishop_notation
    },
    KNIGHT {
        override val notationStringRes: Int
            get() = R.string.knight_notation
    },
    ROOK {
        override val notationStringRes: Int
            get() = R.string.rook_notation
    };
}

enum class LETTER : Key {
    A {
        override val notationStringRes: Int
            get() = R.string.A_notation
    },
    B {
        override val notationStringRes: Int
            get() = R.string.B_notation
    },
    C {
        override val notationStringRes: Int
            get() = R.string.C_notation
    },
    D {
        override val notationStringRes: Int
            get() = R.string.D_notation
    },
    E {
        override val notationStringRes: Int
            get() = R.string.E_notation
    },
    F {
        override val notationStringRes: Int
            get() = R.string.F_notation
    },
    G {
        override val notationStringRes: Int
            get() = R.string.G_notation
    },
    H {
        override val notationStringRes: Int
            get() = R.string.H_notation
    },
}

enum class Number : Key {
    ONE {
        override val notationStringRes: Int
            get() = R.string.one_notation
    },
    TWO {
        override val notationStringRes: Int
            get() = R.string.two_notation
    },
    THREE {
        override val notationStringRes: Int
            get() = R.string.three_notation
    },
    FOUR {
        override val notationStringRes: Int
            get() = R.string.four_notation
    },
    FIVE {
        override val notationStringRes: Int
            get() = R.string.five_notation
    },
    SIX {
        override val notationStringRes: Int
            get() = R.string.six_notation
    },
    SEVEN {
        override val notationStringRes: Int
            get() = R.string.seven_notation
    },
    EIGHT {
        override val notationStringRes: Int
            get() = R.string.eight_notation
    };
}

enum class Special : Key {
    TAKE {
        override val notationStringRes: Int
            get() = R.string.take_notation
    },
    TO_CHECK {
        override val notationStringRes: Int
            get() = R.string.check_notation
    },
    CHECKMATE {
        override val notationStringRes: Int
            get() = R.string.checkmate_notation
    },
    OO {
        override val notationStringRes: Int
            get() = R.string.small_castling_notation
    },
    OOO {
        override val notationStringRes: Int
            get() = R.string.large_castling_notation
    };
}