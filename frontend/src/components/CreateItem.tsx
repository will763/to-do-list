import React, { EventHandler, useEffect, useState } from "react";
import { api } from "../lib/api";


function CreateItem() {
   
   const [value, setValue] = useState('');

   function handleInput(e: React.FormEvent<HTMLInputElement>) {
      setValue(e.currentTarget.value);
   }

   async function handleSubmit(e:React.FormEvent<HTMLFormElement>) {
      e.preventDefault();
      await api.post("/items", {description: value})
         .catch((error) => {console.log(error);});  
      setValue(''); 
   }

   return (
       <form
        className="relative h-10 md:h-12 dark:bg-[#25273c] bg-[#fafafa]
         transition-colors duration-300 flex rounded-[.3rem] items-center pl-4"
         onSubmit={handleSubmit}
       >
           <button className="w-5 h-5 rounded-full border-[1px] dark:border-[#4d5066] border-[#e4e5f1] pointer-events-none " />
           <input
              className="font-josefin-sans flex-1 dark:text-[#e4e5f1] text-[#484b6a] h-4 bg-transparent
               caret-[#5269A5] outline-0 px-3 placeholder:text-[.8rem]
               dark:placeholder:text-[#4d5066] placeholder:text-[#9394a5]" 
              type="text"
              name="item" 
              id="item"
              autoComplete="off"
              placeholder="Create a new todo..." 
              value={value}
              onChange={handleInput}
            />
       </form>
   )
}

export default CreateItem;