import { memo, useMemo } from "react";
import { api } from "../lib/api";
import IconCheck from "./IconCheck";

interface Props {
    id: number;
    description: string,
    completed: boolean;
}

function Item({id, description, completed}:Props) {

    const unDone = useMemo(()=> 'px-3 w-[85%] md:w-11/12 dark:text-[#cacde8] text-[#484b6a] text-[.8rem] cursor-pointer truncate', []);
    const done = useMemo(()=> 'px-3 w-[85%] md:w-11/12 dark:text-[#cacde8] dark:text-[#9394a5] text-[#9394a5] text-[.8rem] cursor-pointer line-through truncate', []);

    async function deleteItem() {
        await api.delete(`/items/${id}`).catch(error => console.log(error));
    }

    async function updateItem() {
        await api.put(`items/${id}`).catch(error => console.log(error));
    }

    return (
        <div
         className="group flex items-center h-10 md:h-12 px-4 border-b-[1px]
          dark:border-b-[#374151]  border-b-[#e4e5f1] active:bg-blue-500 
          active:border-transparent"
        >
            <IconCheck completed={completed} update={updateItem} />
            <p className={completed ? done : unDone} >{description}</p>
            <button
              className="icon-close hidden group-hover:block " 
              onClick={deleteItem}
            />
        </div>
    )
}

export default memo(Item);