package com.geekster.todoApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TodoController {

    @Autowired
    List<Todo> todoList;  //DataSource

    //Create todos -Post APIs
    @PostMapping("todo")
    public String addTodo(@RequestBody Todo myTodo){
        todoList.add(myTodo);
        return "todo added.";
    }

    //get api
    //get all todos:
    @GetMapping("getAllTodos")
    public List<Todo> getAllTodos(){
        return todoList;
    }

    //update todo :
    //send a todo id:id1 and status:s1
    @PutMapping("setTodoStatusById/{id}")
    public String setTodoStatusById(@PathVariable Integer id,@RequestParam boolean flag){
        for(Todo todo : todoList){
            if(todo.getTodoId().equals(id)){
                todo.setTodoStatus((flag));
                return "todo: " +id + " updated to " + flag;
            }
        }
        return "Invalid ID";
    }

    //delete a todo :
    @DeleteMapping("deleteATodo/{id}")
    public String deleteTodo(@PathVariable Integer id){
        for(Todo todo : todoList){
            if(todo.getTodoId().equals(id)){
                todoList.remove(todo);
                return "Todo " + id + " removed";
            }
        }
        return "Invalid ID";
    }

    //delete all todos:
    @DeleteMapping("deleteAllTodos")
    public String deleteAllTodos(){
        todoList.clear();
        return "List cleared";
    }

    //add a list of todos :
    @PostMapping("todos")
    public String addTodos(@RequestBody List<Todo> myTodos){
        /*for(Todo todo : myTodos){
            todoList.add(todo);
        }*/
        todoList.addAll(myTodos);
        return myTodos.size() + " todos were added.";
    }
    //get all undone todos :
    @GetMapping("getUndoneTodos")
    public List<Todo> getAllUndoneTodos(){
        //return todoList.stream().filter(todo -> !todo.isTodoStatus()).collect(Collectors.toList());
        List<Todo> undoneTodos = new ArrayList<>();
        for(Todo todo : todoList){
            if(!todo.isTodoStatus()){
                undoneTodos.add(todo);
            }
        }
        return undoneTodos;
    }

    //delete todos by ids
    @DeleteMapping("todos/id")
    public List<Todo> removeTodos(@RequestBody List<Integer> idList){
        /*for(Todo ogTodo : todoList){
            for(Integer id : idList){
                if(ogTodo.getTodoId().equals(id)) {
                    todoList.remove(ogTodo);
                }
            }
        }*/

        /*for(int i=0; i<todoList.size(); i++){
            Todo ogtodo = todoList.get(i);
            for(int j=0; j<idList.size(); j++){
                if(ogtodo.getTodoId().equals(idList.get(j))) {
                    todoList.remove(ogtodo);
                }
            }
        }*/

        for(Integer id : idList){
            for(int i=0; i<todoList.size(); i++){
                if(id.equals(todoList.get(i).getTodoId())){
                    todoList.remove(todoList.get(i));
                    break;
                }
            }
        }

        return todoList;
    }


}
