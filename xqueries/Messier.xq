declare namespace source = "http://transentia";
declare namespace destination = "http://other";
declare namespace xf = "http://tempuri.org/Messier";

declare function xf:main($src as element(), $str, $n, $b, $dt, $f, $nd)
  as element(destination:data) {
      <destination:data str="{ $str}" n="{ $n }" b="{ $b }" dt="{ $dt }" f="{ $f }" nd="{ $nd }">
        {
        for $m in $src/source:M
          return <destination:m?> { $m/source:CONSTELLATION/text() } </destination:m> (: ONLY CHANGE HERE :)
        }
      </destination:data>
};

declare variable $src as element() external;
declare variable $str external;
declare variable $n external;
declare variable $b external;
declare variable $dt external;
declare variable $f external;
declare variable $nd external;

xf:main($src, $str, $n, $b, $dt, $f, $nd)
