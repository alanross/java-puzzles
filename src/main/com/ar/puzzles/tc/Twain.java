package com.ar.puzzles.tc;

/**
 * http://gskinner.com/RegExr/
 * http://topcoder.g.hatena.ne.jp/caligue/?word=%2A%5BSRM%20169%5D
 * http://www.codeproject.com/KB/dotnet/regextutorial.aspx
 * <p/>
 * SRM 169 DIV 2
 *
 * @author Alan Ross
 */
public class Twain
{
	public Twain()
	{
		System.out.println(
				"0 " +
						getNewSpelling( 1, "i fixed the chrome xerox by the cyclical church" ) + " > " +
						getNewSpelling( 1, "i fixed the chrome xerox by the cyclical church" )
								.compareTo( "i fiksed the chrome zeroks by the cyclical church" ) );

		System.out.println(
				"1 " +
						getNewSpelling( 2, "i fixed the chrome xerox by the cyclical church" ) + " > " +
						getNewSpelling( 2, "i fixed the chrome xerox by the cyclical church" )
								.compareTo( "i fiksed the chrome zeroks bi the ciclical church" ) );

		System.out.println(
				"2 " +
						getNewSpelling( 0, "this is unchanged" ) + " > " +
						getNewSpelling( 0, "this is unchanged" )
								.compareTo( "this is unchanged" ) );

		System.out.println(
				"3 " +
						getNewSpelling( 7, "sch kn x xschrx cknnchc cyck xxceci" ) + " > " +
						getNewSpelling( 7, "sch kn x xschrx cknnchc cyck xxceci" )
								.compareTo( "sk n z zskrks nchk sik zksesi" ) ); //shit

		System.out.println(
				"4 " +
						getNewSpelling( 7, "  concoction   convalescence   cyclical   cello   " ) + " > " +
						getNewSpelling( 7, "  concoction   convalescence   cyclical   cello   " )
								.compareTo( "  konkoktion   konvalesense   siklikal   selo   " ) ); //shit

		System.out.println(
				"5 " +
						getNewSpelling( 7, "" ) + " > " +
						getNewSpelling( 7, "" )
								.compareTo( "" ) );

		System.out.println(
				getNewSpelling( 7, "cck xzz aaaaa" ) + " > " +
						getNewSpelling( 7, "cck xzz aaaaa" ) //shit
								.compareTo( "k z aaaaa" ) );
	}

	public String getNewSpelling( int year, String phrase )
	{
		if( phrase.compareTo( "" ) == 0 )
		{
			return phrase;
		}

		if( year >= 1 )
		{
			//If word starts with "x" replace "x" with "z"
			phrase = phrase.replaceAll( "\\b[x]", "z" );

			//change all remaining "x"s to "ks"s
			phrase = phrase.replaceAll( "(?!\\b[x])x", "ks" );
		}
		if( year >= 2 )
		{
			//Change all "y"s to "i"s.
			phrase = phrase.replaceAll( "y", "i" );
		}
		if( year >= 3 )
		{
			//if a "c" is directly followed by an "e" or "i" change the "c" to an "s".
			phrase = phrase.replaceAll( "ce", "se" );
			phrase = phrase.replaceAll( "ci", "si" );
		}
		if( year >= 4 )
		{
			//If a "c" is directly followed by a "k", remove the "c". 
			//Keep applying this rule as necessary (Example: "cck" becomes "k".)
			phrase = phrase.replaceAll( "(c+k)", "k" );
		}
		if( year >= 5 )
		{
			//If a word starts with "sch", change the "sch" to a "sk".
			phrase = phrase.replaceAll( "\\b(sch)", "sk" );
			//If a "ch" is directly followed by an "r", change the "ch" to a "k".
			phrase = phrase.replaceAll( "chr", "kr" );
			//After applying the above rules, change all "c"s that are not directly 
			//followed by an "h", to a "k". (This includes all "c"s that are the last letter of a word.)
			phrase = phrase.replaceAll( "c(?!h)", "k" );
		}
		if( year >= 6 )
		{
			//If a word starts with "kn" change the "kn" to an "n".
			phrase = phrase.replaceAll( "\\b(kn)", "n" );
		}
		if( year >= 7 )
		{
			//Change all double consonants of the same letter to a single consonant. 
			//A consonant is any letter that is not one of "a, e, i, o, u." 
			//(Example: "apple" becomes "aple"). Keep applying this rule as 
			//necessary (Example: "zzz" becomes "z".)

			char c = 'a';

			for( int i = 0; i < 26; ++i )
			{
				if( c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' )
				{
					c += 1;
					continue;
				}

				phrase = phrase.replaceAll( c + "+", c + "" );
				c += 1;
			}
		}

		return phrase;
	}
}