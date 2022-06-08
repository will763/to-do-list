import { useEffect } from "react";
import { api } from "../lib/api";
import CreateItem from "./CreateItem";
import ExtraInfo from "./ExtraInfo";
import Header from "./Header";
import ListItems from "./ListItems";

function ToDoList() {

  async function clearAllItems() {
    await api.delete('/items/all').catch(error => console.log(error));
  }

  useEffect(()=>{
    window.addEventListener('beforeunload', clearAllItems);

    return () => window.removeEventListener('beforeunload', clearAllItems);
  },[])

  return (
      <div className="w-80 md:w-[26rem] mt-14">
        <Header />
        <CreateItem />
        <ListItems />
        <ExtraInfo />
      </div>
  )
}

export default ToDoList;