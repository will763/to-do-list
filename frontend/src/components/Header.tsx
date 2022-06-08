import { useEffect, useState } from "react";
import sun from '/images/icon-sun.svg';
import moon from '/images/icon-moon.svg';

function Header() {

   const [themeDark, setThemeDark] = useState<boolean>(true);

   useEffect(()=>{
     const root = window.document.documentElement;
     root.classList.toggle("dark");
   },[themeDark])


   return (
       <header className="flex justify-between items-center mb-6">
           <h1 className="font-josefin-sans font-bold tracking-[.5rem] text-[1.6rem] md:text-[1.8rem] ">TODO</h1>
           <div className="w-5 h-5 self-start cursor-pointer">
               <img src={themeDark ? sun : moon} alt="icon sun" onClick={() => setThemeDark(!themeDark)} />
           </div>
       </header>
   )
}

export default Header;