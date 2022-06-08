import axios from "axios";
import { useEffect, useState } from "react";
import useSWR from "swr";
import { api } from "../lib/api";
import FilterList from "./FilterItems";
import RenderItems from "./RenderItems";

const fetcher = (url:string) => axios.get(url + '/items').then(res => res.data)

function ListItems() {
  const [z,setZ] = useState([]);
  const {data, error} = useSWR(import.meta.env.VITE_API_URL, fetcher, { refreshInterval: 1000});
  const [left,setLeft] = useState<number>(0);

  async function deleteAllCompleted() {
    await api.delete("/items").catch(error => console.log(error));
  }

  useEffect(()=>{
    setZ(data);
  }, [data]);

  useEffect(()=>{
    const newArray = data?.filter((data:any) => {
          return data.completed === false;
    });
    setLeft(newArray?.length)
  }, [data]);

  function handleShowItems(data:[]) {
      setZ(data);
  }
  
   return(
       <>
        <main
         className="dark:bg-[#25273c] bg-[#fafafa] rounded-[.3rem] mt-4
          transition-colors duration-300 "
        >
            <div className="flex flex-col h-max min-h-[16rem] ">
                { z?.length > 0 ? <RenderItems data={z} /> 
                  : <h1 className="mx-auto w-max mt-12 dark:text-[#cacaca]/40 
                    text-[#b1b1b1] transition-colors duration-300"
                  >No items yet</h1>
                }
            </div>  
           <footer
            className="flex items-center text-[.75rem] h-10 md:h-12
             dark:text-[#4d5066] text-[#9394a5] justify-between px-4
              border-t-[1px] dark:border-gray-700 "
            >
               <span
                className="dark:hover:text-[#cacde8] hover:text-[#484b6a]
                 transition-colors duration-150 cursor-pointer "
                >
                  {left + ' items left'}
               </span>
               <div className="md:block hidden" >
                 <FilterList handleShowItems={handleShowItems} data={data} />
               </div>
               <span
                className="dark:hover:text-[#cacde8] hover:text-[#484b6a]
                  transition-colors duration-150 cursor-pointer"
                  onClick={deleteAllCompleted}
                >
                    Clear Completed
               </span>
           </footer>
        </main>
          <div className="md:hidden" >
            <FilterList handleShowItems={handleShowItems} data={data} />
          </div>
       </>
   )
}

export default ListItems;