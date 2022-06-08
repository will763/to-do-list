import { useEffect, useState } from "react";
import { ReactSortable} from "react-sortablejs";
import Item from "./Item";

interface TypesData {
  id: number
  description: string
  completed: boolean
}

interface Props {
   data: Array<TypesData>
}

function RenderItems({data}:Props) {

   const [items,setItems] = useState<Array<TypesData>>(data);
   
   useEffect(()=>{
     setItems(data);
   },[data])

    return (
        <ReactSortable list={items} setList={setItems} >
          {items.map(({id,description,completed}) => {
           return <Item 
                    key={id}
                    id={id}
                    description={description}
                    completed={completed} 
                  />
          })}
        </ReactSortable>
    )
  }

export default RenderItems;